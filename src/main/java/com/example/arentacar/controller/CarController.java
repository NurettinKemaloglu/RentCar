package com.example.arentacar.controller;

import com.example.arentacar.business.abstracts.CarService;
import com.example.arentacar.entities.concretes.Car;
import com.example.arentacar.request.BoleanResponce;
import com.example.arentacar.request.CreateCarRequest;
import com.example.arentacar.responses.GetAllCarsResponce;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.arentacar.request.BoleanResponce.BooleanResponse.success;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {
    public  final CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  void addCar(@RequestBody @Valid CreateCarRequest createCarRequest){
        this.carService.addCar(createCarRequest);
    }
    @GetMapping
    public List<GetAllCarsResponce> getAll() {
        return carService.getAll();
    }
    @DeleteMapping
    public BoleanResponce.BooleanResponse deleteCar(@PathVariable(value = "carId") Long carId) {
       carService.deleteModel(carId);
        return success();
    }
    @GetMapping("/page")
    public Page<Car> carPage(@RequestParam Integer pageSize, @RequestParam Integer  page){
        return carService.carPage(pageSize,page);

    }
    @GetMapping("/slice")
    public Slice<Car> slice(Pageable pageable){
        return  carService.slice(pageable);
    }
}
