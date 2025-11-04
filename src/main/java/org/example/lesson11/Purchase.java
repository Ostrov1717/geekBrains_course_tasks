package org.example.lesson11;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private double priceAtPurchase;
    private int quantity;
    private LocalDateTime purchaseDate = LocalDateTime.now();

    public Purchase(Buyer buyer, Product product, double priceAtPurchase, int quantity) {
        this.buyer = buyer;
        this.product = product;
        this.priceAtPurchase = priceAtPurchase;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Purchase{id=" + id +
                ", buyerId=" + buyer.getId() +
                ", productId=" + product.getId() +
                ", priceAtPurchase=" + priceAtPurchase +
                ", quantity=" + quantity +
                '}';
    }
}
