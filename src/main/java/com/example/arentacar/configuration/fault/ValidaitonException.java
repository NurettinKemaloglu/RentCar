package com.example.arentacar.configuration.fault;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

import javax.validation.ValidationException;

@Getter
@Setter
public class ValidaitonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Errors errors;

    public void ValidationException(Errors errors) {
        this.errors = errors;
    }

    public static void throwIfHasErrors(Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getNestedPath());
        }
    }
}
