package com.wimukthi.capstoneproject01.repositories;

import com.wimukthi.capstoneproject01.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
