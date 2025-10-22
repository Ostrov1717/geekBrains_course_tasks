package org.example.lesson4;

public class FruitStore {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 5; i++) {
            appleBox.addFruit(new Apple());
            orangeBox.addFruit(new Orange());
        }

        System.out.println("Weight of apple box: " + appleBox.getWeight());
        System.out.println("Weight of orange box: " + orangeBox.getWeight());

        Box<Apple> anotherAppleBox = new Box<>();
        anotherAppleBox.addFruit(new Apple());
        anotherAppleBox.addFruit(new Apple());

        System.out.println("Before transfer:");
        System.out.println("Apple box weight: " + appleBox.getWeight());
        System.out.println("Another apple box weight: " + anotherAppleBox.getWeight());

        appleBox.transferFruitsTo(anotherAppleBox);

        System.out.println("After transfer:");
        System.out.println("Apple box weight: " + appleBox.getWeight());
        System.out.println("Another apple box weight: " + anotherAppleBox.getWeight());

        orangeBox.transferFruitsTo(orangeBox);
        System.out.println("Orange box weight after transferring to itself: " + orangeBox.getWeight());
    }
}
