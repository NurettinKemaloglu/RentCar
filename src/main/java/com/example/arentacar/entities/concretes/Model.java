package com.example.arentacar.entities.concretes;

import com.example.arentacar.base.AbstractEntity;
import com.example.arentacar.core.utilities.mappers.EntityConstantsUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = EntityConstantsUtil.PREFIX_TB + "models")
public class Model extends AbstractEntity {
    @Column(name = "name", length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id" ,referencedColumnName = "id")
    private Brand brand;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "model")
    private List<Car> cars;

}
