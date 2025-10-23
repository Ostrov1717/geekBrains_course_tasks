package org.example.lesson5;

public class HomePhoneBook {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.addContact("Green", "123-456-7890");
        phonebook.addContact("Green", "098-765-4321");
        phonebook.addContact("Brown", "555-555-5555");
        phonebook.addContact("Brown", "555-555-5555");

        System.out.println("Phone numbers for Green: " + phonebook.getPhoneNumbers("Green"));
        System.out.println("Phone numbers for Brown: " + phonebook.getPhoneNumbers("Brown"));
        System.out.println("Phone numbers for Smith: " + phonebook.getPhoneNumbers("Smith"));
    }
}
