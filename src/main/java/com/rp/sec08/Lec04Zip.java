package com.rp.sec08;

import com.rp.log.Log;
import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Lec04Zip {

    public static void main(String[] args) {
//        Flux.zip(getBody(), getEngine(), getTires())
//                .subscribe(Util.subscriber());
//
//        Flux.zip(getBody(), getEngine(), getTires())
//                .doOnNext(tuple -> {
//                    Log.log("Body: " + tuple.getT1() + "");
//                    Log.log("Engine: " + tuple.getT2() + "");
//                    Log.log("Tire: " + tuple.getT3() + "");
//                    Log.newLine();
//                }).subscribe();

        Flux.just(List.of("Faith", "Ahmed"))
                .collectList()
                .switchIfEmpty(Mono.error(new Throwable("Empty list received")))
                .doOnNext(Log::logLine)
                .subscribe(Util.subscriber());

    }

    private static Flux<String> getBody(){
        return Flux.range(1, 5)
                .map(i -> "body");
    }

    private static Flux<String> getEngine(){
        return Flux.range(1, 3)
                .map(i -> "engine");
    }

    private static Flux<String> getTires(){
        return Flux.range(1, 6)
                .map(i -> "tires");
    }

}
