package com.example.arentacar.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarsResponce {
    private String plate;
    private double dailyPrice;
    private BigDecimal modelYear;
    private BigDecimal state;
    private  String brandName;
    private  String modelName;
}
