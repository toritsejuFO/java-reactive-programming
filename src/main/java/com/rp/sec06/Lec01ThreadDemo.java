package com.rp.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import static com.rp.log.Log.logLine;

public class Lec01ThreadDemo {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            logLine("create");
            fluxSink.next(1);
        }).doOnNext(i -> logLine("next " + i));


        Runnable runnable = () -> flux.subscribe(v -> logLine("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(5);
    }

}
