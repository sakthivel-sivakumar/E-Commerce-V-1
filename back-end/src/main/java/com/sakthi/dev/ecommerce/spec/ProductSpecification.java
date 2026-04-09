package com.sakthi.dev.ecommerce.spec;

import com.sakthi.dev.ecommerce.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    // filter products based on category
    public static Specification<Product> hasCategory(String category){
        return  (root, query, criteriaBuilder)  ->
                criteriaBuilder.
                        equal(root.join("category").get("name"),category);
    }


    // filter products based on price range
    public static Specification<Product> priceBetween(Double minPrice,Double maxPrice){

        return (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .between(root.get("price"),minPrice,maxPrice);
    }

    // filter products above min price
    public static Specification<Product> aboveMinPrice(Double minPrice){

        return (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .greaterThanOrEqualTo(root.get("price"),minPrice);
    }

    // filter products lesser than max price
    public static Specification<Product> belowMaxPrice(Double maxPrice){

        return (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .lessThanOrEqualTo(root.get("price"),maxPrice);
    }

    // filter products based on ratings

    public static Specification<Product> ratingBased(Double rating) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .greaterThanOrEqualTo(root.get("ratings"),rating) ;
    }


    // filter based on keywords
    public static Specification<Product> nameOrDescriptionContains(String keyword) {

        return (root, query, cb) -> {

            if (keyword == null || keyword.trim().isEmpty()) {
                return cb.conjunction();
            }

            String pattern = "%" + keyword.trim().toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("description")), pattern),
                    cb.like(cb.lower(root.join("category").get("name")), pattern)
            );
        };
    }

}