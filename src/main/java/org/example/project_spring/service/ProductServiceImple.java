package org.example.project_spring.service;

import lombok.RequiredArgsConstructor;
import org.example.project_spring.dto.request.ProductRequest;
import org.example.project_spring.dto.respone.BrandRespone;
import org.example.project_spring.dto.respone.BrandResultRespone;
import org.example.project_spring.dto.respone.ProductRespone;
import org.example.project_spring.entity.Brand;
import org.example.project_spring.entity.Product;
import org.example.project_spring.exception.BrandNotFound;
import org.example.project_spring.exception.ProductNotFound;
import org.example.project_spring.repository.BrandRepository;
import org.example.project_spring.repository.ProducRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImple implements ProductService{
    private final ProducRepository producRepository;
    private final BrandRepository brandRepository;
    @Override
    public ProductRespone create(ProductRequest productRequest, MultipartFile file)throws IOException{
        String fileName = file.getOriginalFilename();
        String fileUrl = UUID.randomUUID().toString()+"_"+fileName;
        Path path = Paths.get("uploadProduct");
        String imageUrl="http://localhost:8080/uploadProduct/"+fileUrl;
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        Files.copy(file.getInputStream(), path.resolve(fileUrl));


        Product product = new Product();
        product.setProName(productRequest.getProName());
        product.setPrice(productRequest.getPrice());
        product.setQty(productRequest.getQty());
        product.setImage(imageUrl);
        Brand brand = brandRepository.findById(productRequest.getBrand_id())
                .orElseThrow(()->new BrandNotFound("Not Found"));
        product.setBrand(brand);
        product=producRepository.save(product);;

        ProductRespone productRespone=new ProductRespone();
        productRespone.setId(product.getId());
        productRespone.setProName(product.getProName());
        productRespone.setPrice(product.getPrice());
        productRespone.setQty(product.getQty());
        productRespone.setImage(product.getImage());
        productRespone.setCreatedAt(product.getCreatedAt());
        productRespone.setUpdatedAt(product.getUpdatedAt());

        BrandResultRespone brandRespone = new BrandResultRespone();

        brandRespone.setBrand_id(brand.getBrand_id());
        brandRespone.setBrandName(brand.getBrandName());
        brandRespone.setLogo(brand.getLogo());
        brandRespone.setCreatedAt(brand.getCreatedAt());
        brandRespone.setUpdatedAt(brand.getUpdatedAt());

        productRespone.setBrands(brandRespone);

        return productRespone;
    }
    @Override
    public List<ProductRespone> read(){
        List<Product> products = producRepository.findAll();
        List<ProductRespone> respones = new ArrayList<>();

        for(Product product:products){

            ProductRespone productRespone=new ProductRespone();

            productRespone.setId(product.getId());
            productRespone.setProName(product.getProName());
            productRespone.setPrice(product.getPrice());
            productRespone.setQty(product.getQty());
            productRespone.setImage(product.getImage());
            productRespone.setCreatedAt(product.getCreatedAt());
            productRespone.setUpdatedAt(product.getUpdatedAt());

            Brand brand = product.getBrand();

            BrandResultRespone brandRespone = new BrandResultRespone();

            brandRespone.setBrand_id(brand.getBrand_id());
            brandRespone.setBrandName(brand.getBrandName());
            brandRespone.setLogo(brand.getLogo());
            brandRespone.setCreatedAt(brand.getCreatedAt());
            brandRespone.setUpdatedAt(brand.getUpdatedAt());

            productRespone.setBrands(brandRespone);
            respones.add(productRespone);
        }
        return respones;
    }
    @Override
    public ProductRespone delete(Long id){
        Product product = producRepository.findById(id).orElseThrow(()->new ProductNotFound("Not found"));
        ProductRespone productRespone=new ProductRespone();

        productRespone.setId(product.getId());
        productRespone.setProName(product.getProName());
        productRespone.setPrice(product.getPrice());
        productRespone.setQty(product.getQty());
        productRespone.setImage(product.getImage());
        productRespone.setCreatedAt(product.getCreatedAt());
        productRespone.setUpdatedAt(product.getUpdatedAt());

        Brand brand=product.getBrand();
        BrandResultRespone brandRespone = new BrandResultRespone();

        brandRespone.setBrand_id(brand.getBrand_id());
        brandRespone.setBrandName(brand.getBrandName());
        brandRespone.setLogo(brand.getLogo());
        brandRespone.setCreatedAt(brand.getCreatedAt());
        brandRespone.setUpdatedAt(brand.getUpdatedAt());

        productRespone.setBrands(brandRespone);
        producRepository.delete(product);
        return productRespone;
    }
    @Override
    public ProductRespone update(Long id, ProductRequest productRequest, MultipartFile file)throws IOException{

        Product product = producRepository.findById(id).orElseThrow(()->new ProductNotFound("Product Not foud"));
        String fileName = file.getOriginalFilename();
        String fileUrl = UUID.randomUUID().toString()+"_"+fileName;
        Path path = Paths.get("uploadProduct");
        String imageUrl="http://localhost:8080/uploadProduct/"+fileUrl;

        Files.copy(file.getInputStream(), path.resolve(fileUrl));

        Brand brand = brandRepository.findById(productRequest.getBrand_id())
                .orElseThrow(()->new BrandNotFound("Not Found"));
        product.setProName(productRequest.getProName());
        product.setPrice(productRequest.getPrice());
        product.setQty(productRequest.getQty());
        product.setImage(imageUrl);
        product.setBrand(brand);
        product=producRepository.save(product);;


        ProductRespone productRespone=new ProductRespone();
        productRespone.setId(product.getId());
        productRespone.setProName(product.getProName());
        productRespone.setPrice(product.getPrice());
        productRespone.setQty(product.getQty());
        productRespone.setImage(product.getImage());
        productRespone.setCreatedAt(product.getCreatedAt());
        productRespone.setUpdatedAt(product.getUpdatedAt());


        BrandResultRespone brandRespone = new BrandResultRespone();

        brandRespone.setBrand_id(brand.getBrand_id());
        brandRespone.setBrandName(brand.getBrandName());
        brandRespone.setLogo(brand.getLogo());
        brandRespone.setCreatedAt(brand.getCreatedAt());
        brandRespone.setUpdatedAt(brand.getUpdatedAt());

        productRespone.setBrands(brandRespone);
        return productRespone;
    }
}
