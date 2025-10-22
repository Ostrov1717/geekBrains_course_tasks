package org.example.lesson4;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Box<T extends Fruit> {

    private List<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public float getWeight() {
        if (fruits.isEmpty()) {
            return 0.0f;
        }
        return fruits.size() * fruits.get(0).getWeight();
    }

    public boolean compare(Box<?> anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.0001;
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public void transferFruitsTo(Box<T> anotherBox) {
        if(this == anotherBox) {
            return;
        }
        anotherBox.getFruits().addAll(this.fruits);
        this.fruits.clear();
    }
}
