package com.example.arentacar.controller;

import com.example.arentacar.business.abstracts.ModelService;
import com.example.arentacar.dataAccess.abstracts.ModelRepository;
import com.example.arentacar.request.BoleanResponce;
import com.example.arentacar.request.CreateModelRequest;
import com.example.arentacar.request.UpdateModelRequest;
import com.example.arentacar.responses.GetAllModelResponce;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.arentacar.request.BoleanResponce.BooleanResponse.success;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelController {
    private final ModelService modeLService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateModelRequest createModelRequest) {
      this.modeLService.add(createModelRequest);
    }

    @GetMapping
    public List<GetAllModelResponce> getAll() {
        return modeLService.getAll();
    }

    @DeleteMapping
    public BoleanResponce.BooleanResponse deleteModel(@PathVariable(value = "modelId") Long modelId) {
        modeLService.deleteModel(modelId);
        return success();
    }
    @PutMapping("/{modelId}")
    public  void  updateModel(@PathVariable(value = "modelId")Long modelId,
                              @RequestBody UpdateModelRequest updateModelRequest){
        modeLService.updateModel(modelId,updateModelRequest);
    }
}
