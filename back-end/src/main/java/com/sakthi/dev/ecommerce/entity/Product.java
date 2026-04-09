package com.sakthi.dev.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "name field is required")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "price field is required")
    @PositiveOrZero(message = "Price should be zero or greater than zero")
    private Double price;

    @Column(nullable = false)
    @NotBlank(message = "description field is required")
    private String description;

    @PositiveOrZero(message = "Rating cannot be negative")
    private Double ratings = 0.0;

    @Column(nullable = false)
    @NotBlank(message = "seller field is required")
    private String seller;

    @Column(nullable = false)
    @NotNull(message = "stock field is required")
    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;

    private Integer numberOfReviews = 0;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ProductImage> images;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ProductReview> reviews;

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private Category category;
}