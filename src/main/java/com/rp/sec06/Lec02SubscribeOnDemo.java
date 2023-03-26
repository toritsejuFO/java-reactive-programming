package com.rp.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.rp.log.Log.logLine;

public class Lec02SubscribeOnDemo {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            logLine("create");
            fluxSink.next(1);
        })
                .subscribeOn(Schedulers.newParallel("newParallelName"))
                .doOnNext(i -> logLine("next " + i));

        Runnable runnable = () -> flux
                .doFirst(() -> logLine("first2"))
                .doOnSubscribe(l -> logLine("subscribed: " + l))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> logLine("first1"))
                .subscribe(v -> logLine("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(5);
    }

}
