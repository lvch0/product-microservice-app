package com.lvch0.productservice;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private Long inventoryId;
    private Long productId;
    private Boolean inStock;
}

