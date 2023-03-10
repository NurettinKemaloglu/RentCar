package com.example.arentacar.business.abstracts;

import com.example.arentacar.request.CreateModelRequest;
import com.example.arentacar.request.UpdateModelRequest;
import com.example.arentacar.responses.GetAllModelResponce;

import java.util.List;

public interface ModelService {
    void add(CreateModelRequest createModelRequest);

    List<GetAllModelResponce> getAll();
    void deleteModel(Long modelId);

    void updateModel(Long modelId, UpdateModelRequest updateModelRequest);
}

