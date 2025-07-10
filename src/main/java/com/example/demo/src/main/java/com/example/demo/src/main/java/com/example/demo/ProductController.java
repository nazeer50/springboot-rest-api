package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = new ArrayList<>();

    @GetMapping
    public List<Product> getAll() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return products.stream()
                       .filter(p -> p.getId() == id)
                       .findFirst()
                       .orElse(null);
    }

    @PostMapping
    public String add(@RequestBody Product product) {
        products.add(product);
        return "Product added.";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(updatedProduct.getName());
                p.setPrice(updatedProduct.getPrice());
                return "Product updated.";
            }
        }
        return "Product not found.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        products.removeIf(p -> p.getId() == id);
        return "Product deleted.";
    }
}
