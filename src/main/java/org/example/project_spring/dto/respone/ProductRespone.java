package org.example.project_spring.dto.respone;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class ProductRespone {
    private Long id;
    private String proName;
    private Double price;
    private Integer qty;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BrandResultRespone brands;
}
