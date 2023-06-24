package com.rp.sec08.assignment;

import com.rp.log.Log;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Demo {

    private static Integer initialPrice = 10000;


    public static void main(String[] args) {

        Flux.combineLatest(monthlyStream(), quarterlyStream(),
                        (monthlyValue, quarterlyValue) -> (initialPrice - monthlyValue) * quarterlyValue)
                .subscribe(Util.subscriber());


        Util.sleepSeconds(12);
    }

    private static Flux<Long> monthlyStream() {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1))
                .map(i -> i * 100L)
                .delayElements(Duration.ofSeconds(1))
                .cast(Long.class);
    }


    private static Flux<Double> quarterlyStream() {
        return Flux.generate(syncSink -> syncSink.next(Util.faker().random().nextInt(80, 120) / 100d))
                .startWith(1d)
                .delayElements(Duration.ofSeconds(3))
                .cast(Double.class);
    }

}
