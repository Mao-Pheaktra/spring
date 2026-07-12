package org.example.project_spring.controller;

import lombok.RequiredArgsConstructor;
import org.example.project_spring.dto.request.ProductRequest;
import org.example.project_spring.dto.respone.ApiRespone;
import org.example.project_spring.dto.respone.ProductRespone;
import org.example.project_spring.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ProductRespone create(@ModelAttribute ProductRequest productRequest, @RequestParam("file")MultipartFile file)throws IOException {
        return productService.create(productRequest,file);
    }
    @GetMapping
    public ApiRespone<List<ProductRespone>> read(){
        return new ApiRespone<>("get data successfully",200,productService.read());
    }
    @DeleteMapping("/{id}")
    public ProductRespone delete(@PathVariable Long id){
        return productService.delete(id);
    }
    @PutMapping("/{id}")
    public ProductRespone update(@PathVariable Long id, @ModelAttribute ProductRequest productRequest,@RequestParam("file") MultipartFile file) throws IOException{
        return productService.update(id,productRequest,file);
    }
}
