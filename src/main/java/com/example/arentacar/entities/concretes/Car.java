package com.example.arentacar.entities.concretes;

import com.example.arentacar.base.AbstractEntity;
import com.example.arentacar.core.utilities.mappers.EntityConstantsUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = EntityConstantsUtil.PREFIX_TB +"cars")
public class Car extends AbstractEntity {
    @Column(name = "plate",unique = true)
    private String plate;

    @Column(name = "dailyPrice")
    private double dailyPrice;

    @Column(name = "modelYear")
    private BigDecimal modelYear;

    @Column(name = "state")
    private BigDecimal state;

    @ManyToOne
    @JoinColumn(name = "model_id",referencedColumnName = "id")
    private Model model;
}
