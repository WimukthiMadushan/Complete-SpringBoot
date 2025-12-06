package com.wimukthi.capstoneproject01.repositories;

import com.wimukthi.capstoneproject01.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
