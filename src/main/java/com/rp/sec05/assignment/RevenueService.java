package com.rp.sec05.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RevenueService implements Consumer<PurchaseOrder> {
    private Map<Product, Double> revenue = new HashMap<>();

    public RevenueService() {
        revenue.put(Product.builder().name("Shoe").category("Kids").build(), 0.0);
        revenue.put(Product.builder().name("Ford").category("Automotive").build(), 0.0);
    }

    @Override
    public void accept(PurchaseOrder purchaseOrder) {
        revenue.entrySet().forEach(entry -> {
            if (entry.getKey().getCategory().equals(purchaseOrder.getCategory())) {
                entry.setValue(entry.getValue() + purchaseOrder.getPrice());
            }
        });
    }

    public Flux<Map<Product, Double>> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> revenue);
    }
}
