package org.example.project_spring.dto.request;

import lombok.Data;


@Data
public class ProductRequest {
    private String proName;
    private Double price;
    private Integer qty;
    private String image;
    private Long brand_id;
}
