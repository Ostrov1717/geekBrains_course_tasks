package org.example.lesson13;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.example.lesson13");
        ProductService productService = context.getBean(ProductService.class);
        productService.printAll();
        Cart cart = context.getBean(Cart.class);

        cart.addProduct(productService.findByTitle("Apple"));
        cart.addProduct(productService.findByTitle("Banana"));
        cart.addProduct(productService.findByTitle("Mango"));

        OrderService orderService = context.getBean(OrderService.class);
        orderService.placeOrder();
    }
}
