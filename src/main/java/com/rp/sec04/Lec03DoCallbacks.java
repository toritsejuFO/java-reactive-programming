package com.rp.sec04;

import com.rp.log.Log;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec03DoCallbacks {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            Log.logLine("inside create");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
             fluxSink.complete();
//            fluxSink.error(new RuntimeException("oops"));
            System.out.println("--completed");
        })
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doFirst(() -> Log.logLine("doFirst"))
                .doOnNext(o -> Log.logLine("doOnNext : " + o))
                .doOnRequest(l -> Log.logLine("doOnRequest : " + l))
                .doOnSubscribe(s -> Log.logLine("doOnSubscribe: " + s))
                .doOnError(err -> Log.logLine("doOnError : " + err.getMessage()))
                .doOnTerminate(() -> Log.logLine("doOnTerminate"))
                .doOnCancel(() -> Log.logLine("doOnCancel"))
                .doFinally(signal -> Log.logLine("doFinally 1 : " + signal))
                .doOnDiscard(Object.class, o -> Log.logLine("doOnDiscard : " + o))
                .take(2)
//                .doFinally(signal -> Log.logLine()("doFinally 2 : " + signal))
                .subscribe(Util.subscriber());

    }

}
