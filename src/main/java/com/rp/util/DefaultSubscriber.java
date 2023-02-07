package com.rp.util;

import com.rp.log.Log;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber<T> implements Subscriber<T> {
    private String name = "";

    public DefaultSubscriber(String name) {
        this.name = name + " - ";
    }

    public DefaultSubscriber() {
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T o) {
        Log.logLine(name + "Received : " + o);
    }

    @Override
    public void onError(Throwable throwable) {
        Log.logLine(name + "ERROR : " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        Log.logLine(name + "Completed");
    }
}
