package org.example.lesson8;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    @Getter
    static class Person{
        String name;
        int age;
        int salary;

        Person(String name, int age, int salary){
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
    }

    public static void main(String[] args) {
        //Task 1:
        String[] strings = {"раз", "два", "три", "четыре", "раз", "два","раз","два","раз","два"};
        Map<String,Integer> frequencyMap = Arrays.stream(strings)
                .collect(Collectors.toMap(
                        s -> s,
                        s -> 1,
                        Integer::sum
                ));

        List<String> result = Arrays.stream(strings)
                .collect(Collectors.groupingBy(word -> word, Collectors.summingInt(w -> 1)))
                .entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList());

        System.out.println(result);

        // Task 1.1
        int max = frequencyMap.values().stream()
                .max(Integer::compareTo)
                .orElseThrow();

        List<String> result2 = frequencyMap.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println(result2);

        Person[] persons = {
                new Person("Andrew", 23, 1500),
                new Person("Igor", 23, 2000),
                new Person("Ira", 23, 2500),
                new Person("Victor", 29, 3003),
                new Person("Olga", 31, 3500),
                new Person("Elena", 32, 1000),
    };
    //Task 2: Calculate the average salary of all persons
    double averageSalary = Arrays.stream(persons)
            .mapToDouble(Person::getSalary)
            .average()
            .orElse(0);
    System.out.println("Average salary: " + averageSalary);

    //Task 3: Get the names of the 4 persons with the oldest age
    String res = Arrays.stream(persons)
            .sorted(Comparator.comparing(Person::getAge).reversed())
            .limit(4)
            .map(person->person.getName())
            .collect(Collectors.joining(", ",
                    "4 persons with the oldest age: ", "."));

    System.out.println(res);

    IntStream streamOfChars = "abc".chars();

    }
}
