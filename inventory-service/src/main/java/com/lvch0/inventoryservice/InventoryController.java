package com.lvch0.inventoryservice;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {

    List<Inventory> inventoryList = new ArrayList<Inventory>();

    @GetMapping("/inventory/{productid}")
    public Inventory getInventoryDetails(@PathVariable Long productid) {
        Inventory inventory = getInventoryInfo(productid);

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
