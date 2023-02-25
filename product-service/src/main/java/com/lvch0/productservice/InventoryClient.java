package com.lvch0.productservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/inventory/{productid}")
    public Inventory getInventoryDetails(@PathVariable("productid") Long productid);
}
