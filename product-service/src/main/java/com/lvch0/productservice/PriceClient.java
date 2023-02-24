package com.lvch0.productservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "price-client", url = "http://localhost:8080/")
public interface PriceClient {

    @GetMapping("/price/{productid}")
    public Price getPriceDetails(@PathVariable("productid") Long productid);
}
