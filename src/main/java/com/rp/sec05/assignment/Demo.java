package com.rp.sec05.assignment;

import com.rp.util.Util;

public class Demo {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        InventoryService inventoryService = new InventoryService();
        RevenueService revenueService = new RevenueService();

        orderService.stream().subscribe(inventoryService);
        orderService.stream().subscribe(revenueService);

        inventoryService.stream()
                .subscribe(Util.subscriber("INVENTORY"));
        revenueService.stream()
                .subscribe(Util.subscriber("REVENUE"));

        Util.sleepSeconds(60);

    }
}
