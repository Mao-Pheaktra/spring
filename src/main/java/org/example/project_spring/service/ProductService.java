package org.example.project_spring.service;

import org.example.project_spring.dto.request.ProductRequest;

import org.example.project_spring.dto.respone.ProductRespone;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductRespone create(ProductRequest productRequest, MultipartFile file)throws IOException;
    List<ProductRespone> read();
    ProductRespone delete(Long id);
    ProductRespone update(Long id, ProductRequest productRequest, MultipartFile file)throws IOException;
}
