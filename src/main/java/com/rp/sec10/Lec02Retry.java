package com.rp.sec10;

import com.rp.log.Log;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {
    public static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        getIntegers()
                .retry(1)
                .subscribe(Util.subscriber("Main"));
    }

    public static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> Log.logLine(s + "  -- subscribed"))
                .doOnComplete(() -> Log.logLine("-- completed"))
                .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(s -> Log.logLine(s + " -- error"));
    }
}
