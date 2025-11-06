package org.example.lesson11.connections.many_to_many_adv;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyer {
    @Id
    private Long id;

    @OneToMany(mappedBy = "buyer")
    private List<Purchase> purchases;
}
