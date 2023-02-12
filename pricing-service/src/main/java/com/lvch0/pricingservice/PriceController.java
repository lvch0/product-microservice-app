package com.lvch0.pricingservice;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

@RestController
public class PriceController {

    List<Price> priceList = new ArrayList<Price>();

    @GetMapping("/price/{productid}")
    public Mono<Price> getPriceDetails(@PathVariable Long productid) {
        Mono<Price> price = Mono.just(getPriceInfo(productid));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return price;
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
