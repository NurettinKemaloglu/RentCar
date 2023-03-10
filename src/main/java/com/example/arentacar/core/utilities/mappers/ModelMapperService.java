package com.example.arentacar.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forResponce();

    ModelMapper forRequest();
}
