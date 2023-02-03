package com.rp.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {
    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just("ball")
                .map(String::length)
                .map(l -> l / 0);

        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
