package org.example.lesson11.connections.many_to_many_adv;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // дополнительные поля
    private double priceAtPurchase;

    private LocalDateTime purchaseDateTime = LocalDateTime.now();
}
