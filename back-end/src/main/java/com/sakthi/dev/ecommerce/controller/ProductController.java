package com.sakthi.dev.ecommerce.controller;

import com.sakthi.dev.ecommerce.dto.ProductRequestDTO;
import com.sakthi.dev.ecommerce.dto.ProductResponseDTO;
import com.sakthi.dev.ecommerce.entity.Product;
import com.sakthi.dev.ecommerce.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO> > getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction){

        Page<ProductResponseDTO> products = productService.getAllProducts(page,size,sortBy,direction);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id){
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDTO>> searchProducts(@RequestParam(required = false) String category,
                                                        @RequestParam(name = "min" , required = false) Double minPrice,
                                                        @RequestParam(name = "max" , required = false) Double maxPrice,
                                                        @RequestParam(required = false) Double rating,
                                                        @RequestParam(required = false) String keywords,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "4") int size,
                                                        @RequestParam(defaultValue = "id") String sortBy,
                                                        @RequestParam(defaultValue = "asc") String direction){
        Page<ProductResponseDTO> filteredProducts = productService
                                         .searchProducts(category,minPrice,maxPrice,rating,keywords,page, size,sortBy,direction);
        return ResponseEntity.ok().body(filteredProducts);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO)
    {
        return ResponseEntity.ok().body(productService.addProduct(productRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok().body(productService.updateProduct(id, productRequestDTO));
    }


}
