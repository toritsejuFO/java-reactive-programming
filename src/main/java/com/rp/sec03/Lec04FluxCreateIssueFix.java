package com.rp.sec03;

import com.rp.log.Log;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateIssueFix {

    public static void main(String[] args) {

        // only one instance of FluxSink
        Flux.create(fluxSink -> {
            String country;
            do {
                country = Util.faker().country().name();
                Log.logLine("Emitting::" + country);
                fluxSink.next(country);
            } while (!country.equals("Canada") && !fluxSink.isCancelled());
            fluxSink.complete();
        }).take(3).subscribe(Util.subscriber("SUB1"));

    }

}
