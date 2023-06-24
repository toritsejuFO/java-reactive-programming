package com.rp.sec08;

import com.rp.sec08.helper.Airlines;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec03Merge {

    public static void main(String[] args) {

        Flux.merge(
                Airlines.getFlights("Qatar", 5),
                Airlines.getFlights("Emirates", 10),
                Airlines.getFlights("AA", 7)
        ).subscribe(Util.subscriber());

        Util.sleepSeconds(10);


    }


}
