package org.example.lesson8;


import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class App {
    private static Logger log = Logger.getLogger(App.class.getName());

    @Getter
    @ToString
    static class Product {
        private int price;
        private String name;
        public Product(int price, String name) {
            this.price = price;
            this.name = name;
        }
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");

        Optional<String> stream = list.stream().filter(element -> {
            log.info(element);
            log.info("filter() was called");
            return element.contains("2");
        }).map(element -> {
            log.info(element);
            log.info("map() was called");
            return element.toUpperCase();
        }).findFirst();


        List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
                new Product(14, "orange"), new Product(13, "lemon"),
                new Product(23, "bread"), new Product(13, "sugar"));

        IntSummaryStatistics statistics = productList.stream()
                .collect(Collectors.summarizingInt(Product::getPrice));

        System.out.println(statistics);

        Map<Boolean, List<Product>> mapPartioned = productList.stream()
                .collect(Collectors.partitioningBy(element -> element.getPrice() > 15));
        System.out.println(mapPartioned);
    }
}
