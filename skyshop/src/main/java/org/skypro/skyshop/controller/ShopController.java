package org.skypro.skyshop.controller;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.ShopError;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.UUID;

public class ShopController {
    @Autowired
    private SearchService searchService;

    @Autowired
    private BasketService basketService;

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return StorageService.returnAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return StorageService.returnAllArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProductToBasket(@PathVariable("id") UUID id) {
        basketService.addProductToBasket(id);
        return "Product successfully added to the basket";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") UUID id) {
        return StorageService.getProductById(id)
                .map(product -> ResponseEntity.ok(product))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ShopError("PRODUCT_NOT_FOUND", "Product not found")));
    }


}