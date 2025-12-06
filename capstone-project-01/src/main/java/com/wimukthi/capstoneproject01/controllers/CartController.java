package com.wimukthi.capstoneproject01.controllers;


import com.wimukthi.capstoneproject01.dtos.AddItemToCartRequest;
import com.wimukthi.capstoneproject01.dtos.CartDto;
import com.wimukthi.capstoneproject01.entities.Cart;
import com.wimukthi.capstoneproject01.entities.CartItem;
import com.wimukthi.capstoneproject01.mappers.CartMapper;
import com.wimukthi.capstoneproject01.repositories.CartRepository;
import com.wimukthi.capstoneproject01.repositories.ProductRepositories;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private CartRepository cartRepository;
    private ProductRepositories productRepository;
    private CartMapper cartMapper;

    @PostMapping("/")
    public ResponseEntity<CartDto> createCart(){
        var cart = new Cart();
        cartRepository.save(cart);
        var cartDto = cartMapper.toCartDto(cart);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartDto> createCartItem(
            @PathVariable UUID cartId,
            @RequestBody AddItemToCartRequest request
    ){
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        var product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        var cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cart.getItems().add(cartItem);
        }
        cartRepository.save(cart);

        var cartItemDto = cartMapper.toCartDto(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(
            @PathVariable UUID cartId
    ){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        var cartDto = cartMapper.toCartDto(cart);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

}
