package com.sakthi.dev.ecommerce.repository;

import com.sakthi.dev.ecommerce.entity.Category;
import com.sakthi.dev.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {

    // The below line tell JPA to create query by parsing method Name
    // List<Product> findProductByCategoryNameIgnoreCase(String category);

}
