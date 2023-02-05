package com.rp.sec02.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockPriceGenerator {
    private final Integer START_PRICE = 100;
    private Integer pricePerTime = START_PRICE;

    public StockPriceGenerator() {
    }

    private static Integer getDifference() {
        List<Integer> diff = new ArrayList<>(10);
        Flux.range(-5, 10)
                .subscribe(diff::add);
        return diff.get(new Random().nextInt(10));
    }

    public Flux<Integer> startPriceGenerator() {
        return Flux.interval(Duration.ofSeconds(1))
                .doOnNext(i -> pricePerTime += getDifference())
                .map(i -> pricePerTime);
    }
}
