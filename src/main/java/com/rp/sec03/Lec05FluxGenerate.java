package com.rp.sec03;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            synchronousSink.next(country); // only 1, loop is maintained by the generate method
//            synchronousSink.next(Util.faker().country().name()); // this fails
            if (country.toLowerCase().equals("canada")) synchronousSink.complete();
        }).subscribe(Util.subscriber("SUB1"));

    }


}
