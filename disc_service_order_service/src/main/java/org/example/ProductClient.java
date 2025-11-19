package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service")
interface ProductClient {
    @GetMapping("/products")
    List<String> getProducts();
}
