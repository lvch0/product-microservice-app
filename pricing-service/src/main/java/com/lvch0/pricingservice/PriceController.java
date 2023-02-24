package com.lvch0.pricingservice;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController 
public class PriceController {

    List<Price> priceList = new ArrayList<Price>();

    @GetMapping("/price/{productid}")
    public Price getPriceDetails(@PathVariable Long productid) {
        Price price = getPriceInfo(productid);

        return new Price(price.getPriceId(), price.getProductId(), price.getOriginalPrice(),
                price.getDiscountedPrice());
    }

    private Price getPriceInfo(Long productid) {
        populatePriceList();
        for (Price p : priceList) {
            if (productid.equals(p.getProductId())) {
                return p;
            }
        }
        return null;
    }

    private void populatePriceList() {
        priceList.clear();
        priceList.add(new Price(201L, 101L, 1999, 999));
        priceList.add(new Price(202L, 102L, 40, 10));
        priceList.add(new Price(203L, 103L, 120, 10));
    }
}
