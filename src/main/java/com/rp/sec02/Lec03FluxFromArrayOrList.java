package com.rp.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromArrayOrList {
    public static void main(String[] args) {
        List<String> strings = List.of("a", "b", "c");

        Flux.fromIterable(strings)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        Integer[] intArr = {1, 2, 3, 4};

        Flux.fromArray(intArr)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
