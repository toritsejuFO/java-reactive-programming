package com.rp.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.rp.log.Log.logLine;

public class Lec05PubSubOn {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    logLine("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(1);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> logLine("next (close to source) " + i));

        flux
                .publishOn(Schedulers.parallel())
                .doOnNext(i -> logLine("next (close to subscriber) " + i))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> logLine("sub " + v));

        Util.sleepSeconds(5);

    }

}
