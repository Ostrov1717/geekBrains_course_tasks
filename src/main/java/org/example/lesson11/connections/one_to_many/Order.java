package org.example.lesson11.connections.one_to_many;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
