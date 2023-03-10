package com.example.arentacar.dataAccess.abstracts;

import com.example.arentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}
