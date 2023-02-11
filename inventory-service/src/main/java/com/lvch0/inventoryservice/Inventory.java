package com.lvch0.inventoryservice;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private Long inventoryId;
    private Long productId;
    private Boolean inStock;
}

