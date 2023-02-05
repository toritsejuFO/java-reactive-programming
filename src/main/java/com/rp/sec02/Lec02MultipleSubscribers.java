package com.rp.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {
    public static void main(String[] args) {
        Flux<Integer> intFlux = Flux.just(1, 2, 3, 4);
        Flux<Integer> evenFlux = intFlux.filter(i -> i % 2 == 0);

        intFlux.subscribe(
                Util.onNext("SUB 1"),
                Util.onError(),
                Util.onComplete()
        );

        evenFlux.subscribe(
                Util.onNext("SUB 2"),
                Util.onError(),
                Util.onComplete()
        );
    }
}
