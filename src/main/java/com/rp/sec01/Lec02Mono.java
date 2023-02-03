package com.rp.sec01;

import com.rp.log.Log;
import reactor.core.publisher.Mono;

public class Lec02Mono {
    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(Log::logLine);
    }
}
