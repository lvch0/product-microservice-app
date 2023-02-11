package com.lvch0.pricingservice;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
  private Long priceId;
  private Long productId;
  private Integer originalPrice;
  private Integer discountedPrice;  
}
