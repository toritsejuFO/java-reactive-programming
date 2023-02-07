package com.rp.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec09SwitchIfEmpty {

    public static void main(String[] args) {

        getOrderNumbers()
                .filter(i -> i > 10)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());

    }

    // this could be redis cache
    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }

    // database
    private static Flux<Integer> fallback() {
        return Flux.range(20, 10);
    }

}
