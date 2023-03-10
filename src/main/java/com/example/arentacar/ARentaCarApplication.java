package com.example.arentacar;

import com.example.arentacar.core.utilities.exceptions.BusinessException;
import com.example.arentacar.core.utilities.exceptions.ProblemDetails;
import com.example.arentacar.core.utilities.exceptions.ValidationProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;


@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
@RestControllerAdvice // bütün controllerlar exception handlera tabi demek
public class ARentaCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ARentaCarApplication.class, args);
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleBusinessException(BusinessException businessException) {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setMessage(businessException.getMessage());

        return problemDetails;

    }
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setMessage("Validation Exception");
        validationProblemDetails.setValidationErrors(new HashMap<String,String>());

        for (FieldError fieldError :methodArgumentNotValidException.getBindingResult().getFieldErrors()){
            validationProblemDetails.getValidationErrors().put(fieldError.getField(),fieldError.getDefaultMessage());
        }
                return  validationProblemDetails;

        }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
