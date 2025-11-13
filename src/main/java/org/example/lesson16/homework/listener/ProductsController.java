package org.example.lesson16.homework.listener;


import lombok.RequiredArgsConstructor;
import org.example.lesson16.homework.CountView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;
    private final ProductStatsService statsService;

    @GetMapping
    public List<Product> showProductsList() {
        return productsService.getAllProducts();
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        productsService.add(product);
        return "Product added successfully";
    }
    @CountView
    @GetMapping("/{id}")
    public Product showOneProduct(@PathVariable(value = "id") Long id) {
        return productsService.getById(id);
    }

    @GetMapping("/viewCounts")
    public Map<String, Integer> getViewCounts() {
        return statsService.getTop3Viewed();
    }
}
