package org.example.lesson16.homework.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductViewStatsListener {

    private final ProductStatsService statsService;

    @EventListener
    public void handleProductViewed(ProductViewedEvent event) {
        statsService.incrementViewCount(event.getProduct());
    }
}
