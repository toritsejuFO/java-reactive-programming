package com.rp.sec01;

import reactor.core.publisher.Mono;

import static com.rp.log.Log.logLine;
import static com.rp.util.Util.onComplete;
import static com.rp.util.Util.onError;
import static com.rp.util.Util.onNext;
import static com.rp.util.Util.sleepSeconds;

public class Lec08MonoFromRunnable {
    public static void main(String[] args) {
        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(
                        onNext(),
                        onError(),
                        onComplete()
                );
    }

    public static Runnable timeConsumingProcess() {
        sleepSeconds(3);
        return () -> logLine("Task done");
    }
}
