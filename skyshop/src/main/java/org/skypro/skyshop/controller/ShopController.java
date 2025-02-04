package org.skypro.skyshop.controller;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
public class ShopController {
    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return StorageService.returnAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return StorageService.returnAllArticles();
    }
}
