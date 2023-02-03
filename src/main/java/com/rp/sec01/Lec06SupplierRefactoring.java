package com.rp.sec01;

import com.rp.log.Log;
import com.rp.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec06SupplierRefactoring {
    public static void main(String[] args) {
        Util.startTimer();
        getName();
        String name = getName()
                .subscribeOn(Schedulers.boundedElastic())
//                .subscribe(Util.onNext());
                .block();
        Log.logLine(name);
        getName();
        Util.stopTimer();

//        Util.sleepSeconds(4); // block main thread to print name;
    }

    private static Mono<String> getName() {
        Log.logLine("Entered getName method");
        return Mono.fromSupplier(() -> {
            Log.logLine("Generating name..");
            Util.sleepSeconds(3);
            return Util.faker().name().fullName();
        }).map(String::toUpperCase);
    }
}
