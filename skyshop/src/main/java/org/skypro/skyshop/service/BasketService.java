package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.UserBasket;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        storageService.getProductById(id)
                .ifPresentOrElse(product -> productBasket.addProduct(product.getId()),
                        () -> { throw new IllegalArgumentException("Product with id " + id + " not found"); });
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> productsInBasket = productBasket.getAllProducts();
        List<BasketItem> basketItems = productsInBasket.entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()).orElseThrow(), entry.getValue()))
                .collect(Collectors.toList());

        int total = basketItems.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        return new UserBasket(basketItems, total);
    }
}
