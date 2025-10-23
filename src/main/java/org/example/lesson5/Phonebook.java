package org.example.lesson5;

import java.util.*;

public class Phonebook {

    private final Map<String, Set<String>> phonebook = new HashMap<>();

    public void addContact(String name, String phoneNumber) {
        phonebook.computeIfAbsent(name, k -> new HashSet<>()).add(phoneNumber);
    }

    public Set<String> getPhoneNumbers(String name) {
        return phonebook.getOrDefault(name, Collections.emptySet());
    }

}
