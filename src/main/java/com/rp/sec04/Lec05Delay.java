package com.rp.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec05Delay {

    public static void main(String[] args) {

        Flux.range(1,100)
//                .publishOn(Schedulers.parallel())
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

}
