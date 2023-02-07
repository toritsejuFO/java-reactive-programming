package com.rp.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {

    public static void main(String[] args) {

        Flux.range(0, 10)
                .handle((i, syncSink) -> {
                    if (i == 7) {
                        syncSink.complete();
                    } else {
                        syncSink.next(i);
                    }
                })
                .subscribe(Util.onNext());

    }

}
