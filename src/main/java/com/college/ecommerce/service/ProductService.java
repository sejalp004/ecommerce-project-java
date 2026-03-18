package com.college.ecommerce.service;

import com.college.ecommerce.model.Product;
import com.college.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public void saveProduct(Product product) {
        repo.save(product);
    }
}
