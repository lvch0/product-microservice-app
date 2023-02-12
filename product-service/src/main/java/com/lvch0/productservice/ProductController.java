package com.lvch0.productservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
@RestController
public class ProductController {

    List<ProductInfo> productList = new ArrayList<ProductInfo>();

    public WebClient webClient = WebClient.create();

    @GetMapping("/product/details/{productid}")
    public Mono<Product> getProductDetails(@PathVariable Long productid) {
        System.out.println("1");
        // !Get Name and Desc from product-service
        Mono<ProductInfo> productInfo = Mono.just(getProductInfo(productid));
        // !Get Price from pricing-service
        Mono<Price> price = webClient.get().uri("http://localhost:8080/price/{productid}", productid).retrieve()
                .bodyToMono(Price.class);
        // !Get stock Avail from inventory-service
        Mono<Inventory> inventory = webClient.get().uri("http://localhost:8003/inventory/{productid}", productid)
                .retrieve()
                .bodyToMono(Inventory.class);

        System.out.print("2");

        return Mono.zip(productInfo, price, inventory)
                .map(tuple -> new Product(tuple.getT1().getProductId(), tuple.getT1().getProductName(),
                        tuple.getT1().getProductDesc(), tuple.getT2().getDiscountedPrice(),
                        tuple.getT3().getInStock()));
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
