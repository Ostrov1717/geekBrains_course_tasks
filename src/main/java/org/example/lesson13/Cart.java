package org.example.lesson13;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class Cart {

    private List<Product> cart=new ArrayList<>();

    public void addProduct(Product product){
        System.out.println("Added to cart: "+product);
        cart.add(product);
    }
}
