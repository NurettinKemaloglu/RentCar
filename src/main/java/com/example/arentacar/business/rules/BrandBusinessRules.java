package com.example.arentacar.business.rules;

import com.example.arentacar.core.utilities.exceptions.BusinessException;
import com.example.arentacar.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    private BrandRepository brandRepository;

    // iş kuralını static yapmak durumunda değiliz zaten singleton kullanıcağız
    public void checkIfBrandNameExists(String brandName) {
        if (this.brandRepository.existsByBrandName(brandName)) {
            throw new BusinessException("Brand name already exists"); // java exception type

        }
    }
}
