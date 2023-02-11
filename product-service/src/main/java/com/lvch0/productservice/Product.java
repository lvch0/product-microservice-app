package com.lvch0.productservice;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long productId;
    private String productName;
    private String productDesc;
    private Integer productPrice;
    private Boolean productStock;
}
