package com.example.arentacar.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelRequest{
        @NotNull
        @NotBlank // boşlukta geçmesin
        @Size(min = 3, max = 20)
       private String name;

        @NotNull
        private Long brandId;

}
