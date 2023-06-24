package com.rp.sec08.helper;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Airlines {

    public static Flux<String> getFlights(String name, int randomizer){
        return Flux.range(1, Util.faker().random().nextInt(1, randomizer))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> name + " " + Util.faker().random().nextInt(100, 999))
                .filter(i -> Util.faker().random().nextBoolean());
    }
}
