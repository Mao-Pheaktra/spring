package org.example.project_spring.service;

import lombok.RequiredArgsConstructor;
import org.example.project_spring.dto.request.BrandRequest;
import org.example.project_spring.dto.respone.BrandRespone;
import org.example.project_spring.entity.Brand;
import org.example.project_spring.entity.Product;
import org.example.project_spring.exception.BrandNotFound;
import org.example.project_spring.repository.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandServiceImple implements BrandService{
    private final BrandRepository brandRepository;

    @Override
    public BrandRespone create(BrandRequest brandRequest, MultipartFile file)throws IOException{
        String fileName = file.getOriginalFilename();
        String fileUrl = UUID.randomUUID().toString()+"_"+fileName;
        Path path = Paths.get("uploadBrand");
        String logoUrl="http://localhost:8080/uploadBrand/"+fileUrl;
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        Files.copy(file.getInputStream(), path.resolve(fileUrl));
        Brand brand = new Brand();
        brand.setBrandName(brandRequest.getBrandName());
        brand.setLogo(logoUrl);
        brand=brandRepository.save(brand);

        BrandRespone brandRespone = new BrandRespone();
        brandRespone.setBrand_id(brand.getBrand_id());
        brandRespone.setBrandName(brand.getBrandName());
        brandRespone.setLogo(brand.getLogo());
        brandRespone.setCreatedAt(brand.getCreatedAt());
        brandRespone.setUpdatedAt(brand.getUpdatedAt());

        return brandRespone;
    }
    @Override
    public BrandRespone readById(Long brand_id) {
        Brand brand = brandRepository.findById(brand_id)
                .orElseThrow(() -> new BrandNotFound("Brand not found"));

        BrandRespone response = new BrandRespone();
        response.setBrand_id(brand.getBrand_id());
        response.setBrandName(brand.getBrandName());
        response.setLogo(brand.getLogo());
        response.setCreatedAt(brand.getCreatedAt());
        response.setUpdatedAt(brand.getUpdatedAt());

        return response;
    }
    @Override
    public List<BrandRespone> readAll() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandRespone> respones = new ArrayList<>();
        for (Brand brand: brands) {
            BrandRespone brandRespone = new BrandRespone();
            brandRespone.setBrand_id(brand.getBrand_id());
            brandRespone.setBrandName(brand.getBrandName());
            brandRespone.setLogo(brand.getLogo());
            brandRespone.setCreatedAt(brand.getCreatedAt());
            brandRespone.setUpdatedAt(brand.getUpdatedAt());
            respones.add(brandRespone);
        }
        return respones;
    }
    @Override
    public BrandRespone delete(Long brand_id){
        Brand brand = brandRepository.findById(brand_id).orElseThrow(()->new BrandNotFound("Brand Not Found"));
        BrandRespone brandRespone = new BrandRespone();
        brandRespone.setBrand_id(brand.getBrand_id());
        brandRespone.setBrandName(brand.getBrandName());
        brandRespone.setLogo(brand.getLogo());
        brandRespone.setCreatedAt(brand.getCreatedAt());
        brandRespone.setUpdatedAt(brand.getUpdatedAt());

        brandRepository.delete(brand);
        return brandRespone;
    }
    @Override
    public BrandRespone update(Long brand_id, BrandRequest brandRequest, MultipartFile file)throws IOException{
        Brand brand = brandRepository.findById(brand_id).orElseThrow(()->new BrandNotFound("Brand Not Found"));
        String fileName = file.getOriginalFilename();
        String fileUrl = UUID.randomUUID().toString()+"_"+fileName;
        Path path = Paths.get("uploadBrand");
        String logoUrl="http://localhost:8080/uploadBrand/"+fileUrl;

        Files.copy(file.getInputStream(), path.resolve(fileUrl));

        brand.setLogo(logoUrl);
        brand.setBrandName(brandRequest.getBrandName());
        brand=brandRepository.save(brand);

        BrandRespone brandRespone = new BrandRespone();
        brandRespone.setBrand_id(brand.getBrand_id());
        brandRespone.setBrandName(brand.getBrandName());
        brandRespone.setLogo(brand.getLogo());
        brandRespone.setCreatedAt(brand.getCreatedAt());
        brandRespone.setUpdatedAt(brand.getUpdatedAt());


        return brandRespone;
    }
}
