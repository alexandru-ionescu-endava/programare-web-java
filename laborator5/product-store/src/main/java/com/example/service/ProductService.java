package com.example.service;

import com.example.exception.DuplicateProductException;
import com.example.model.Product;
import com.example.model.ProductUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1, "Laptop", 999.99));
        products.add(new Product(2, "Smartphone", 499.99));
        products.add(new Product(3, "Tablet", 299.99));
    }

    public List<Product> getProducts(String sort) {

        if (sort.equals("desc"))
            return products.stream()
                    .sorted(Comparator.comparing(Product::getPrice))
                    .toList();

        return products;
    }

    public Product findById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        Integer id = new Random().nextInt(4, 50);

        if (findById(id) != null) {
            throw new DuplicateProductException();
        }

        product.setId(id);
        products.add(product);
    }

    public boolean update(Integer id,
                          ProductUpdateRequest updateRequest) {

        Product product = findById(id);
        if (product != null) {
            product.setTitle(updateRequest.title());
            product.setPrice(updateRequest.price());
            return true;
        }
        return false;
    }

}
