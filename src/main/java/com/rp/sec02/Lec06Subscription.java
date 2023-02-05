package com.rp.sec02;

import com.rp.log.Log;
import com.rp.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec06Subscription {
    public static void main(String[] args) {

        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        Log.logLine("Received Sub : " + subscription);
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.logLine("onNext : " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.logLine("onError : " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.logLine("onComplete");
                    }
                });

        Util.sleepSeconds(3);

        atomicReference.get().request(3);
        Util.sleepSeconds(5);
        atomicReference.get().request(3);
        Util.sleepSeconds(5);
        Log.logLine("Cancelling...");
        atomicReference.get().cancel();
        Util.sleepSeconds(3);
        atomicReference.get().request(4);

        Util.sleepSeconds(3);
    }
}
