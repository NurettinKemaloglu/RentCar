package com.example.arentacar.business.concretes;

import com.example.arentacar.business.rules.BrandBusinessRules;
import com.example.arentacar.core.utilities.mappers.Validations;
import com.example.arentacar.business.abstracts.BrandService;
import com.example.arentacar.core.utilities.mappers.ModelMapperService;
import com.example.arentacar.dataAccess.abstracts.BrandRepository;
import com.example.arentacar.entities.concretes.Brand;
import com.example.arentacar.request.CreateBrandRequest;
import com.example.arentacar.request.UpdateBrandRequest;
import com.example.arentacar.responses.GetAllBrandsResponse;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;


    @Override
    @Transactional(readOnly = true)
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .map(brand -> this.modelMapperService.forResponce().
                        map(brand, GetAllBrandsResponse.class)).toList();


    }

    @Override
    @Transactional
    public void createBrand(CreateBrandRequest createBrandRequest) {
        this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
        Brand brands = this.modelMapperService.forRequest()
                .map(createBrandRequest, Brand.class);
        brandRepository.save(brands);
    }

    @Override
    @Transactional(readOnly = true)
    public Brand getAllBrandId(Long brandId) {
        return brandRepository.findById(brandId).orElse(null);
    }

    @Override
    @Transactional
    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    @Transactional
    public void updateBrand(Long brandId, UpdateBrandRequest updatebrandRequest) {
        brandRepository.findById(brandId).ifPresentOrElse(post -> {
            post.setBrandName(updatebrandRequest.getName());
            brandRepository.save(post);
        }, () -> {
            throw new ServiceException(Validations.POST_ID_INVALID);
        });
    }


}
