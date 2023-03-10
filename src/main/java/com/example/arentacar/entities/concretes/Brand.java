package com.example.arentacar.entities.concretes;

import com.example.arentacar.base.AbstractEntity;
import com.example.arentacar.core.utilities.mappers.EntityConstantsUtil;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = EntityConstantsUtil.PREFIX_TB + "brands")
public class Brand extends AbstractEntity {

    @Column(name = "brand_name",length = 60,nullable = false)
    private  String brandName;
    @OneToMany(mappedBy = "brand" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Model> models;

    }


