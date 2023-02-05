package com.rp.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {
    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3, 4, 5);
        Stream<Integer> integerStream = integerList.stream();

//        integerStream.forEach(Log::logLine);
//        integerStream.forEach(Log::logLine); // stream is operated upon already and will throw error

//        Flux<Integer> flux = Flux.fromStream(integerStream);
//
//        flux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
//        flux.subscribe(Util.onNext(), Util.onError(), Util.onComplete()); // stream is operated upon already and will throw error

        Flux<Integer> flux = Flux.fromStream(() -> integerList.stream()); // stream is created everytime by supplier

        flux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        flux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
