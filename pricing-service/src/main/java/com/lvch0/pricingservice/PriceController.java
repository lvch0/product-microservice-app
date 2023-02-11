package com.lvch0.pricingservice;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class PriceController {

    @Autowired
    private RestTemplate restTemplate;

    List<Price> priceList = new ArrayList<Price>();

    @GetMapping("/price/{productid}")
    public Price getPriceDetails(@PathVariable Long productid) {
        Price price = getPriceInfo(productid);
        // !Get Exchange value
        Integer exgVal = restTemplate.getForObject("http://localhost:8004/currexg/from/USD/to/YEN", ExgVal.class)
                .getExgVal();
        return new Price(price.getPriceId(), price.getProductId(), price.getOriginalPrice(),
                Math.multiplyExact(exgVal, price.getDiscountedPrice()));
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
