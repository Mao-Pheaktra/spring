package org.example.project_spring.dto.respone;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class BrandResultRespone {
    private Long brand_id;
    private String brandName;
    private String logo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
