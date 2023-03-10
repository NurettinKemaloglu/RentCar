package com.example.arentacar.business.abstracts;

import com.example.arentacar.entities.concretes.Brand;
import com.example.arentacar.request.CreateBrandRequest;
import com.example.arentacar.request.UpdateBrandRequest;
import com.example.arentacar.responses.GetAllBrandsResponse;


import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();

    void createBrand(CreateBrandRequest createBrandRequest);

    Brand getAllBrandId(Long brandId);

    void deleteBrand(Long brandId);

    void updateBrand(Long brandId, UpdateBrandRequest updatebrandRequest);
}
