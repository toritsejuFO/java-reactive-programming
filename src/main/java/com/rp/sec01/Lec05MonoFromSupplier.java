package com.rp.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Lec05MonoFromSupplier {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> getName();
        Mono<String> monoSupplier = Mono.fromSupplier(supplier);
        monoSupplier.subscribe(Util.onNext());

        Callable<String> callable = () -> getName();
        Mono<String> monoCallable = Mono.fromCallable(callable);
        monoCallable.subscribe(Util.onNext());
    }

    private static String getName(){
        System.out.println("Generating name..");
        return Util.faker().name().fullName();
    }
}
