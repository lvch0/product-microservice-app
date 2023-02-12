package com.lvch0.inventoryservice;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

@RestController
public class InventoryController {

    List<Inventory> inventoryList = new ArrayList<Inventory>();

    @GetMapping("/inventory/{productid}")
    public Mono<Inventory> getInventoryDetails(@PathVariable Long productid) {
        Mono<Inventory> inventory = Mono.just(getInventoryInfo(productid));

        return inventory;
    }

    private Inventory getInventoryInfo(Long productid) {
        populateInventoryList();
        for (Inventory i : inventoryList) {
            if (productid.equals(i.getProductId())) {
                return i;
            }
        }
        return null;
    }

    private void populateInventoryList() {
        inventoryList.clear();
        inventoryList.add(new Inventory(101L, 101L, true));
        inventoryList.add(new Inventory(102L, 102L, false));
        inventoryList.add(new Inventory(103L, 103L, true));
    }
}
