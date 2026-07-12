package org.example.project_spring.service;

import org.example.project_spring.dto.request.BrandRequest;
import org.example.project_spring.dto.respone.BrandRespone;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BrandService {
    BrandRespone create(BrandRequest brandRequest, MultipartFile file)throws IOException;
    BrandRespone read(Long brand_id);
    BrandRespone delete(Long brand_id);
    BrandRespone update(Long brand_id, BrandRequest brandRequest, MultipartFile file)throws IOException;
}
