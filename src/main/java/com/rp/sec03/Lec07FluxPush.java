package com.rp.sec03;

import com.rp.sec03.helper.NameProducer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxPush {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();

        Flux.push(nameProducer)
                .subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            new Thread(nameProducer::produce).start();
        }

        Util.sleepSeconds(5);

    }
}
