package com.example.springdataredis.service;

import com.example.springdataredis.entity.Product;
import com.example.springdataredis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product addProduct(Product product) {
        return this.productDao.save(product);
    }

    public List<Product> findAllProducts() {
        return this.productDao.findAll();
    }
    @Cacheable(key = "#id",value = "Product")
    public Product findProductById(int id) {
        System.out.println("called findProductById() from DB");
        return this.productDao.findProductById(id);
    }
    @CacheEvict(key = "#id",value = "Product")
    public String deleteProduct(int id) {
        return this.productDao.deleteProduct(id);
    }

}
