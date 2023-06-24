package com.rp.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.rp.log.Log.logLine;

public class Lec04PublishOn {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    logLine("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> logLine("next (close to source) " + i));

        flux
                .doFirst(() -> logLine("Runnable first"))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> logLine("next (close to subscriber) " + i))
                .publishOn(Schedulers.parallel())
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> logLine("sub " + v));

        Util.sleepSeconds(5);

    }

}
