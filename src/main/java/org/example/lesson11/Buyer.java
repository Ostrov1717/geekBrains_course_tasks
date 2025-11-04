package org.example.lesson11;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "buyers")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "buyers_products",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    @ToString.Exclude //!!! исключается, чтобы избежать рекурсии если есть аннотакции @ToString или @Data на классе
    private List<Product> productList;

//    для варианта с сущностью Purchase
//    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
//    @ToString.Exclude
//    private List<Purchase> purchases = new ArrayList<>();

    public Buyer(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Buyer{id=" + id + ", name='" + name + "'}";
    }
}
