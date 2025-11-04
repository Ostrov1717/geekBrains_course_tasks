package org.example.lesson11;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String productName;

    private double price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "productList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private List<Buyer> buyerList;

//    для варианта с сущностью Purchase
//    @OneToMany(mappedBy = "product")
//    @ToString.Exclude
//    private List<Purchase> purchases = new ArrayList<>();


    public Product(String productName, double price) {
        this.productName = productName;
        this.price = price;
        this.buyerList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + productName + "', price=" + price + "}";
    }
}
