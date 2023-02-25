package com.lvch0.productservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "pricing-service")
public interface PriceClient {

    @GetMapping("/price/{productid}")
    public Price getPriceDetails(@PathVariable("productid") Long productid);
}
