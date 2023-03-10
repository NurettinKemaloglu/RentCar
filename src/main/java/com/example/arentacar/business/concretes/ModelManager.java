package com.example.arentacar.business.concretes;

import com.example.arentacar.business.abstracts.ModelService;
import com.example.arentacar.core.utilities.mappers.ModelMapperService;
import com.example.arentacar.core.utilities.mappers.Validations;
import com.example.arentacar.dataAccess.abstracts.ModelRepository;
import com.example.arentacar.entities.concretes.Model;
import com.example.arentacar.request.CreateModelRequest;
import com.example.arentacar.request.UpdateModelRequest;
import com.example.arentacar.responses.GetAllModelResponce;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapperService modelMapperService;

    @Override
    @Transactional
    public void add(CreateModelRequest createModelRequest) {
        Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
        this.modelRepository.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetAllModelResponce> getAll() {
        List<Model> models = modelRepository.findAll();
        return models.stream().map(model -> modelMapperService.forResponce().map(model, GetAllModelResponce.class)).toList();

    }

    @Override
    @Transactional
    public void deleteModel(Long modelId) {
        modelRepository.deleteById(modelId);
    }

    @Override
    @Transactional
    public void updateModel(Long modelId, UpdateModelRequest updateModelRequest) {
        modelRepository.findById(modelId).ifPresentOrElse(post -> {
            post.setName(updateModelRequest.getModelName());
            modelRepository.save(post);
        }, () -> {
            throw new ServiceException(Validations.POST_MODEL_ID_INVALID);
        });

    }
}
