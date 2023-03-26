package com.rp.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.rp.log.Log.logLine;

public class Lec06Parallel {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(i -> logLine("next " + i))
                .subscribe(v -> logLine("sub " + v));

        Util.sleepSeconds(5);

    }


}
