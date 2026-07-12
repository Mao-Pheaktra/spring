package org.example.project_spring.controller;


import lombok.RequiredArgsConstructor;
import org.example.project_spring.dto.request.BrandRequest;
import org.example.project_spring.dto.respone.ApiRespone;
import org.example.project_spring.dto.respone.BrandRespone;
import org.example.project_spring.service.BrandService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    @PostMapping
    public BrandRespone create(@ModelAttribute BrandRequest brandRequest, @RequestParam("file") MultipartFile file) throws IOException {
        return brandService.create(brandRequest,file);
    }
    @GetMapping("/{brand_id}")
    public ApiRespone<BrandRespone> read(@PathVariable Long brand_id){
        return new ApiRespone<>("get data successfully",200,brandService.read(brand_id));
    }
    @DeleteMapping("/{brand_id}")
    public BrandRespone delete(@PathVariable Long brand_id){
        return brandService.delete(brand_id);
    }
    @PutMapping("/{brand_id}")
    public BrandRespone update(@PathVariable Long brand_id, @ModelAttribute BrandRequest brandRequest,@RequestParam("file") MultipartFile file) throws IOException{
        return brandService.update(brand_id,brandRequest,file);
    }

}
