package com.lvch0.pricingservice;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExgVal {
    private Long id;
    private Currencies from;
    private Currencies to;
    private Integer exgVal;
}