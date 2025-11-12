package com.example.controller;

import com.example.model.Product;
import com.example.model.ProductUpdateRequest;
import com.example.proxy.DummyJsonProxy;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final DummyJsonProxy dummyJsonProxy;

    @GetMapping("/external")
    public ResponseEntity<?> getProductsFromExternalApi() {
        var products = dummyJsonProxy.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false, defaultValue = "asc") String sort) {
        var list = productService.getProducts(sort);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        var product = productService.findById(id);

        if (product != null)
            return ResponseEntity.ok(product);

        return ResponseEntity.noContent().build();
    }

//    deserializare JSON -> Java Object
//    serializare Java Object -> JSON

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        productService.addProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody ProductUpdateRequest updateRequest) {
        try {
            boolean updated = productService.update(id, updateRequest);
            if (updated)
                return new ResponseEntity<>(HttpStatus.OK);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
