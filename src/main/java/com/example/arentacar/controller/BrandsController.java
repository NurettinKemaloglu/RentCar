package com.example.arentacar.controller;

import com.example.arentacar.business.abstracts.BrandService;
import com.example.arentacar.entities.concretes.Brand;

import com.example.arentacar.request.BoleanResponce;
import com.example.arentacar.request.CreateBrandRequest;
import com.example.arentacar.request.UpdateBrandRequest;
import com.example.arentacar.responses.GetAllBrandsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.arentacar.request.BoleanResponce.BooleanResponse.success;


@RestController
@RequestMapping("api/brands")
public class BrandsController {
    private BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/getall")
    public List<GetAllBrandsResponse> getAll() {
        return brandService.getAll();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createBrand(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
      this.brandService.createBrand(createBrandRequest);

    }

    @GetMapping("/{brandId}")
    public ResponseEntity<Brand> getAllBrandId(@PathVariable Long brandId) {
        return ResponseEntity.ok(brandService.getAllBrandId(brandId));
    }

    @DeleteMapping("/{brandId}")
    public BoleanResponce.BooleanResponse deleteBrand(@PathVariable Long brandId) {
        brandService.deleteBrand(brandId);
        return success();
    }

    @PutMapping("/{brandId}")
    public void updateBrand(@PathVariable(value = "brandId") Long brandId,
                            @RequestBody UpdateBrandRequest updatebrandRequest) {
        brandService.updateBrand(brandId, updatebrandRequest);
    }

}
