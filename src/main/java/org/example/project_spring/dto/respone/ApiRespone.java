package org.example.project_spring.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRespone<T>{
    private String msg;
    private Integer status;
    private T data;
}
