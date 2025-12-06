package com.wimukthi.capstoneproject01.repositories;

import com.wimukthi.capstoneproject01.entities.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepositories extends JpaRepository<Product, Long> {

}
