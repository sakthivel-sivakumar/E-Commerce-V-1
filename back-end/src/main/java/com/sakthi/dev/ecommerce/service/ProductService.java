package com.sakthi.dev.ecommerce.service;

import com.sakthi.dev.ecommerce.dto.ProductRequestDTO;
import com.sakthi.dev.ecommerce.dto.ProductResponseDTO;
import com.sakthi.dev.ecommerce.entity.Category;
import com.sakthi.dev.ecommerce.entity.Product;
import com.sakthi.dev.ecommerce.repository.CategoryRepository;
import com.sakthi.dev.ecommerce.repository.ProductRepository;
import com.sakthi.dev.ecommerce.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // add product
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO){

        Category category = categoryRepository.findByName(productRequestDTO.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));

            Product product = Product.builder()
                    .name(productRequestDTO.getName())
                    .description(productRequestDTO.getDescription())
                    .price(productRequestDTO.getPrice())
                    .seller(productRequestDTO.getSeller())
                    .stock(productRequestDTO.getStock())
                    .category(category)
                    .build();

            productRepository.save(product);

            return ProductResponseDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .category(product.getCategory().getName())
                    .ratings(product.getRatings())
                    .build();

    }


    public Page<ProductResponseDTO> getAllProducts(int page, int size, String sortBy, String direction){

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage.map(product ->
                ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .category(product.getCategory().getName())
                        .ratings(product.getRatings())
                        .build()
        );

    }

    public ProductResponseDTO getProductById(Long id){
        return productRepository
                .findById(id).
                map(product ->
                        ProductResponseDTO.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .price(product.getPrice())
                                .category(product.getCategory().getName())
                                .ratings(product.getRatings())
                                .build()
                )
                .orElseThrow(()-> new RuntimeException("Product id not exists "));
    }

    public Page<ProductResponseDTO> searchProducts(String category, Double minPrice,
                                        Double maxPrice, Double rating,
                                        String keyword, int page,
                                        int size,String sortBy,String direction){

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Product> spec = Specification.allOf();

        if (category != null && !category.isEmpty()) {
            spec = spec.and(ProductSpecification.hasCategory(category));
        }

        if(minPrice == null && maxPrice != null){
            spec = spec.and(ProductSpecification.belowMaxPrice(maxPrice));
        }
        else if(minPrice != null && maxPrice == null){
            spec = spec.and(ProductSpecification.aboveMinPrice(minPrice));
        }
        else if(minPrice != null && maxPrice != null){
            spec = spec.and(ProductSpecification.priceBetween(minPrice, maxPrice));
        }



        if (rating != null) {
            spec = spec.and(ProductSpecification.ratingBased(rating));
        }

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and(ProductSpecification.nameOrDescriptionContains(keyword));
        }

        Page<Product> productPage = productRepository.findAll(spec, pageable);

        return productPage.map(product ->
                ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .category(product.getCategory().getName())
                        .ratings(product.getRatings())
                        .build()
        );
    }


    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid Product id : " + id));
        productRepository.delete(product);

    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto){

        // 1. Find existing product
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // 2. Find category
        Category category = categoryRepository.findByName(dto.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        //  3. Update fields
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setSeller(dto.getSeller());
        product.setStock(dto.getStock());
        product.setCategory(category);

        // 4. Save
        Product updatedProduct = productRepository.save(product);

        //  5. Return DTO
        return mapToDTO(updatedProduct);
    }


    public ProductResponseDTO mapToDTO(Product product){
                return ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .category(product.getCategory().getName())
                        .ratings(product.getRatings())
                        .build();
    }





}

