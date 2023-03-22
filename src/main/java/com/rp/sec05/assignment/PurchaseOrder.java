package com.rp.sec05.assignment;

import com.rp.util.Util;
import lombok.Data;

@Data
public class PurchaseOrder {

    private Double price;
    private String name;
    private String category;
    private Integer quantity;

    public PurchaseOrder() {
        this.price = Double.valueOf(Util.faker().commerce().price());
        this.name = Util.faker().commerce().productName();
        this.category = Util.faker().commerce().department();
        this.quantity = Util.faker().random().nextInt(1, 9);
    }
}
