package org.example.lesson13;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ProductService {

    private List<Product> products;

    void printAll(){
        System.out.println(products);
    }

    public Product findByTitle(String title){
        return products.stream()
                .filter(product -> product.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    @PostConstruct
    void initProducts(){
        Product product1 = new Product(1L, "Apple", 1.0);
        Product product2 = new Product(2L, "Banana", 0.5);
        Product product3 = new Product(3L, "Orange", 0.8);
        Product product4 = new Product(4L, "Grapes", 2.0);
        Product product5 = new Product(5L, "Mango", 1.5);
        this.products = List.of(product1, product2, product3, product4, product5);
    }
}
