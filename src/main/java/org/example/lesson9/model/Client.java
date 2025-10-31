package org.example.lesson9.model;

import org.example.lesson9.annotations.Column;
import org.example.lesson9.annotations.Table;

@Table(title = "clients")
public class Client {
    @Column
    int id;
    @Column
    String name;
    @Column
    int age;
    @Column
    boolean isActive;
    String telephone;

    public Client(int id, String name, int age, boolean isActive, String telephone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.telephone = telephone;
    }
}
