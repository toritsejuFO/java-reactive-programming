package com.rp.sec08;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec02Concat {

    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("A", "B", "C");
        Flux<String> flux2 = Flux.error(new RuntimeException("Oops"));
        Flux<String> flux3 = Flux.just("X", "Y", "Z");

        Flux<String> flux = Flux.concatDelayError(flux1, flux2, flux3);

        flux.subscribe(Util.subscriber());

    }

}
