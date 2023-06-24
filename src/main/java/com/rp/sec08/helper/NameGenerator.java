package com.rp.sec08.helper;

import com.rp.log.Log;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private List<String> cache = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(syncSink -> {
                    String name = Util.faker().name().name();
                    Log.logLine("Generated Fresh Name");
                    Util.sleepSeconds(1);
                    cache.add(name);
                    syncSink.next(name);
                })
                .startWith(getFromCache())
                .cast(String.class);
    }

    private Flux<String> getFromCache() {
        Log.logLine("Returned from Cache");
        return Flux.fromIterable(cache);
    }
}
