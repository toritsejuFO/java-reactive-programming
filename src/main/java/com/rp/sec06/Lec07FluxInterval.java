package com.rp.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec07FluxInterval {

    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1))
                .doOnNext(Util.onNext())
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(Util.onNext())
                .subscribe(Util.subscriber("sub: "));

        Util.sleepSeconds(5);
    }

}
