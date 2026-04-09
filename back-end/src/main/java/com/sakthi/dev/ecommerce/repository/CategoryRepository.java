package com.sakthi.dev.ecommerce.repository;

import com.sakthi.dev.ecommerce.entity.Category;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);
}
