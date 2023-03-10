package com.example.arentacar.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    @NotNull
    @NotBlank
    private String plate;
    @NotNull
    @NotBlank
    private double dailyPrice;
    @NotNull
    @NotBlank
    private BigDecimal modelYear;
    @NotNull
    @NotBlank
    private BigDecimal state;
    @NotNull
    @NotBlank
    private int modelId;

}
