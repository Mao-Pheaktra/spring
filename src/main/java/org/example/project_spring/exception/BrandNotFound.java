package org.example.project_spring.exception;

public class BrandNotFound extends RuntimeException{
    public BrandNotFound(String message){
        super(message);
    }
}
