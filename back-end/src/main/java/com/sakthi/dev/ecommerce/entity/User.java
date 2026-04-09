package com.sakthi.dev.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class User {

    @Id
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Double ratings = 0.0;
    private String seller;
    private Integer stock;
    private Integer numOfReviews = 0;
}
