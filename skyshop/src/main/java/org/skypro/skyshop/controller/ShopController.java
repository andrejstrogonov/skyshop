package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

public class ShopController {
    @Autowired
    private SearchService searchService;

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
}