package org.example.lesson11.connections.many_to_many_adv;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Purchase {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double priceAtPurchase;

    private LocalDateTime purchaseDateTime = LocalDateTime.now(); // дополнительные поля
}
