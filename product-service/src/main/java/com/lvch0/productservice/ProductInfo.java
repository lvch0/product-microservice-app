package com.lvch0.productservice;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private Long productId;
    private String productName;
    private String productDesc;
}
