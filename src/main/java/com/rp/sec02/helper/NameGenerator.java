package com.rp.sec02.helper;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    public static List<String> getNamesList(int count) {
        List<String> names = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            names.add(getName());
        }
        return names;
    }

    public static Flux<String> getNamesFlux(int count) {
        return Flux.range(0, count)
                .map(i -> getName());
    }

    private static String getName() {
        Util.sleepSeconds(1);
        return Util.faker().name().fullName();
    }
}
