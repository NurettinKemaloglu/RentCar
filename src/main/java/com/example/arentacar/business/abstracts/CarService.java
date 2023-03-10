package com.example.arentacar.business.abstracts;

import com.example.arentacar.entities.concretes.Car;
import com.example.arentacar.request.CreateCarRequest;
import com.example.arentacar.responses.GetAllCarsResponce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CarService {
    void addCar(CreateCarRequest createCarRequest);

    List<GetAllCarsResponce> getAll();

    void deleteModel(Long carId);

    Page<Car> carPage(Integer pageSize, Integer page);

    Slice<Car> slice(Pageable pageable);
}
