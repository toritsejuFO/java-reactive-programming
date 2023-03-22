package com.rp.sec05.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InventoryService implements Consumer<PurchaseOrder> {

    private List<Product> products = new ArrayList<>();

    public InventoryService() {
        products.add(new Product("Shoe", "Kids", 100));
        products.add(new Product("Ford", "Automotive", 100));
    }

    @Override
    public void accept(PurchaseOrder purchaseOrder) {
        products.forEach(p -> {
            if (p.getCategory().equals(purchaseOrder.getCategory())) {
                p.setQuantity(p.getQuantity() - purchaseOrder.getQuantity());
            }
        });
    }

    public Flux<List<Product>> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> products);
    }

}
