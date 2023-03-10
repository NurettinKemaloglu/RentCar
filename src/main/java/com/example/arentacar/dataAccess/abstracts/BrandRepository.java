package com.example.arentacar.dataAccess.abstracts;

import com.example.arentacar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByBrandName(String brandName); // String jpa keywords

}
