package com.example.springdataredis.controller;

import com.example.springdataredis.entity.Product;
import com.example.springdataredis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return this.productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable int id) {
        return this.productService.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id) {
        return this.productService.deleteProduct(id);
    }

}
