package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final ProductClient productClient;

    @GetMapping("/orders/create")
    public String createOrder() {
        List<String> products = productClient.getProducts();
        return "Заказ создан. Доступные товары: " + products;
    }
}
