package com.wimukthi.capstoneproject01.dtos;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartItemDto {
    private CartProductDto product;
    private int quantity;
    private BigDecimal totalPrice;
}