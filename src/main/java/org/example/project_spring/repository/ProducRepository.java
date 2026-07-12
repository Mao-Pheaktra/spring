package org.example.project_spring.repository;

import org.example.project_spring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducRepository extends JpaRepository<Product,Long>{

}
