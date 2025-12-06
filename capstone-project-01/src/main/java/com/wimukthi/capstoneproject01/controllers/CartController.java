package com.wimukthi.capstoneproject01.controllers;


import com.wimukthi.capstoneproject01.dtos.CartDto;
import com.wimukthi.capstoneproject01.entities.Cart;
import com.wimukthi.capstoneproject01.mappers.CartMapper;
import com.wimukthi.capstoneproject01.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private CartRepository cartRepository;
    private CartMapper cartMapper;

    @PostMapping("/")
    public ResponseEntity<CartDto> createCart(){
        var cart = new Cart();
        cartRepository.save(cart);
        var cartDto = cartMapper.toCartDto(cart);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);


    }

}
