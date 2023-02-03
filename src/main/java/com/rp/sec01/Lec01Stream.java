package com.rp.sec01;

import com.rp.log.Log;

import java.util.stream.Stream;

public class Lec01Stream {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(1)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i * i;
                });

        integerStream.forEach(Log::logLine);
    }
}
