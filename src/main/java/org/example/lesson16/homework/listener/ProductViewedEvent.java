package org.example.lesson16.homework.listener;

import lombok.Getter;

@Getter
public class ProductViewedEvent {
    private final Object source;
    private final Product product;

    public ProductViewedEvent(Object source, Product product) {
        this.source = source;
        this.product = product;
    }
}
