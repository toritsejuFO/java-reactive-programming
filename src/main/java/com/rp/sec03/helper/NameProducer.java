package com.rp.sec03.helper;

import com.rp.util.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {
    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void produce() {
        String thread = Thread.currentThread().getName();
        fluxSink.next(thread + " - " + Util.faker().country().name());
    }
}
