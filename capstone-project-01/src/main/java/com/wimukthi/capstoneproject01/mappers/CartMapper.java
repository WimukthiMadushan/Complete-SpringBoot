package com.wimukthi.capstoneproject01.mappers;

import com.wimukthi.capstoneproject01.dtos.CartDto;
import com.wimukthi.capstoneproject01.dtos.CartItemDto;
import com.wimukthi.capstoneproject01.entities.Cart;
import com.wimukthi.capstoneproject01.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice" , expression = "java(cart.getTotalPrice()")
    CartDto toCartDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
