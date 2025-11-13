package org.example.lesson16.homework.listener;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ProductStatsService {

    private final Map<Product, AtomicInteger> productViewCount = new ConcurrentHashMap<>();

    public void incrementViewCount(Product product) {
        productViewCount
                .computeIfAbsent(product, p -> new AtomicInteger(0))
                .incrementAndGet();
    }

    public Map<String, Integer> getTop3Viewed() {
        return productViewCount.entrySet().stream()
                .sorted(Map.Entry.<Product, AtomicInteger>comparingByValue(
                        Comparator.comparingInt(AtomicInteger::get)).reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        e -> e.getKey().getTitle(),
                        e -> e.getValue().get(),
                        (a, b) -> a,
                        LinkedHashMap::new));
    }
}
