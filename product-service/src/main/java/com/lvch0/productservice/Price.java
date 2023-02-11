package com.lvch0.productservice;

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
