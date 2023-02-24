package com.lvch0.productservice;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    List<ProductInfo> productList = new ArrayList<ProductInfo>();

    @Autowired
    private PriceClient priceClient;

    @Autowired
    private InventoryClient inventoryClient;

    @GetMapping("/product/details/{productid}")
    public Product getProductDetails(@PathVariable Long productid) {
        // !Get Name and Desc from product-service
        ProductInfo productInfo = getProductInfo(productid);
        // !Get Price from pricing-service
        Price price = priceClient.getPriceDetails(productid);
        // !Get stock Avail from inventory-service
        Inventory inventory = inventoryClient.getInventoryDetails(productid);

        return new Product(productInfo.getProductId(), productInfo.getProductName(), productInfo.getProductDesc(),
                price.getDiscountedPrice(),
                inventory.getInStock());
    }

    private ProductInfo getProductInfo(Long productid) {
        populateProductList();
        for (ProductInfo p : productList) {
            if (productid.equals(p.getProductId())) {
                return p;
            }
        }
        return null;
    }

    private void populateProductList() {
        productList.clear();
        productList.add(new ProductInfo(101L, "iPhone", "iPhone is damn expensive"));
        productList.add(new ProductInfo(102L, "Book", "Naked Lunch by William S. Burroughs"));
        productList.add(new ProductInfo(103L, "Vinyl", "Kind of Blue by Miles Davis"));
    }
}
