package com.rp.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.rp.log.Log.logLine;

public class Lec03SubscribeOnMultipleItems {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    logLine("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(1);
                        Util.sleepSeconds(1);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> logLine("next " + i));

        Runnable runnable = () -> flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> logLine("sub " + v));

        for (int i = 0; i < 40; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(5);
    }

}
