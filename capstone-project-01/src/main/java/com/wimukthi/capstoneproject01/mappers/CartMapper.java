package com.wimukthi.capstoneproject01.mappers;

import com.wimukthi.capstoneproject01.dtos.CartDto;
import com.wimukthi.capstoneproject01.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toCartDto(Cart cart);
}
