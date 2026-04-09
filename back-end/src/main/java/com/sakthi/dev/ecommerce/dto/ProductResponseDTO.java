package com.sakthi.dev.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private Double price;
    private String category;
    private Double ratings;
}