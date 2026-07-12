package org.example.project_spring.exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String message){
        super(message);
    }
}
