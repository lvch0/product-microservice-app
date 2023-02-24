package com.lvch0.productservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "inventory-client", url = "http://localhost:8003")
public interface InventoryClient {
    @GetMapping("/inventory/{productid}")
    public Inventory getInventoryDetails(@PathVariable("productid") Long productid);
}
