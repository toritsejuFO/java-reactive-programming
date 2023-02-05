package com.rp.util;

import com.github.javafaker.Faker;
import com.rp.log.Log;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Util {
    private static final Faker FAKER = Faker.instance();
    private static final StopWatch timer = StopWatch.createStarted();

    public static Consumer<Object> onNext() {
        return (o) -> Log.logLine("RECEIVED: " + o);
    }

    public static Consumer<Object> onNext(String message) {
        return (o) -> Log.logLine("RECEIVED: " + o + " ::: " + message);
    }

    public static Consumer<Throwable> onError() {
        return (err) -> Log.logLine("ERROR: " + err.getMessage());
    }

    public static Runnable onComplete() {
        return () -> Log.logLine("COMPLETED");
    }

    public static Runnable onComplete(String message) {
        return () -> Log.logLine("COMPLETED: " + message);
    }

    public static Faker faker() {
        return FAKER;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startTimer() {
        if (timer.isStarted()) timer.stop();
        timer.reset();
        timer.start();
    }

    public static void stopTimer() {
        if (timer.isStarted()) {
            timer.stop();
            long timeElapsed = timer.getTime(TimeUnit.MILLISECONDS);
            if (timeElapsed < 1000L)
                Log.logLine("TIME ELAPSED: " + timeElapsed + "ms");
            else
                Log.logLine("TIME ELAPSED: " + (float)timeElapsed/1000 + "s");
        }
    }
}
