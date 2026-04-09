package com.sakthi.dev.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "product name field is required")
    private String name;

    @NotNull(message = "price field is required")
    @PositiveOrZero(message = "Price should be zero or greater than zero")
    private Double price;

    @NotBlank(message = "description field is required")
    private String description;

    @NotBlank(message = "category field is required")
    private String category;

    @NotBlank(message = "seller field is required")
    private String seller;


    @NotNull(message = "stock field is required")
    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;
}
