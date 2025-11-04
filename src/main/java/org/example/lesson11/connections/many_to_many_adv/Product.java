package org.example.lesson11.connections.many_to_many_adv;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Product {
    @Id
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases;
}
