package com.rp.sec02.assignment;

import com.rp.log.Log;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
//        AtomicReference<Boolean> endProgram = new AtomicReference<>(false);
        CountDownLatch latch = new CountDownLatch(1);

        StockPriceGenerator stockPriceGenerator = new StockPriceGenerator();

        stockPriceGenerator.startPriceGenerator()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription sub;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        sub = subscription;
                        Log.logLine("Subscribed");
                        sub.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer price) {
                        Log.logLine("New Price Alert: " + price);
                        if (price > 110 || price < 90) {
                            sub.cancel();
                            latch.countDown();
//                            endProgram.set(true);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        latch.countDown();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                    }
                });

//        while (!endProgram.get());
        latch.await();

        Log.logLine("Program ended");
    }
}
