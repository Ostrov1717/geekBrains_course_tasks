package org.example.lesson13;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private Cart cart;

    public void placeOrder(){
        double total = cart.getCart().stream()
                .mapToDouble(Product::getCost)
                .sum();
        System.out.println(cart.getCart()+" Total cost: "+total);
    }
}
