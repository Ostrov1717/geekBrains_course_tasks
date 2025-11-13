package org.example.lesson16.homework;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;

    private final Map<Product, Integer> productViewCount = new HashMap<>();

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void incrementViewCount(Product product) {
        productViewCount.put(product, productViewCount.getOrDefault(product, 0) + 1);
    }

    public Map<String,Integer> getProductViewCounts() {
        return productViewCount.entrySet().stream()
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getTitle(),
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
}
