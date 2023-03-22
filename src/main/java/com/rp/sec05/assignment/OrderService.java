package com.rp.sec05.assignment;

import org.apache.commons.lang3.ObjectUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class OrderService {

    Flux<PurchaseOrder> purchaseOrderFlux;

    public Flux<PurchaseOrder> stream() {
        return ObjectUtils.defaultIfNull(purchaseOrderFlux, purchaseOrderFlux = getOrderStream());
    }

    public Flux<PurchaseOrder> getOrderStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder())
                .publish()
                .refCount(1);
    }

}
