package com.rp.sec05.assignment;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private String category;
    private Integer quantity;
}
