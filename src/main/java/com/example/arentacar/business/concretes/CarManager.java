package com.example.arentacar.business.concretes;

import com.example.arentacar.business.abstracts.CarService;
import com.example.arentacar.core.utilities.mappers.ModelMapperService;
import com.example.arentacar.dataAccess.abstracts.CarRepository;
import com.example.arentacar.entities.concretes.Car;
import com.example.arentacar.request.CreateCarRequest;
import com.example.arentacar.responses.GetAllCarsResponce;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;


    @Override
    @Transactional
    public void addCar(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest()
                .map(createCarRequest, Car.class);
        this.carRepository.save(car);

    }

    @Override
    @Transactional(readOnly = true)
    public List<GetAllCarsResponce> getAll() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(model -> modelMapperService.forResponce()
                        .map(cars, GetAllCarsResponce.class)).toList();

    }

    @Override
    @Transactional
    public void deleteModel(Long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public Page<Car> carPage(Integer pageSize, Integer page) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return carRepository.findAll(pageable);
    }

    // Slice ile çok fazla veri varsa page işleminde kullanırız 1M VE YA   1M veri listeleyeceksek..performans kazandırır
    //spring boot da yeni gelen özelliktir.
    @Override
    public Slice<Car> slice(Pageable pageable) {
        return  carRepository.findAll(pageable);
    }

}

