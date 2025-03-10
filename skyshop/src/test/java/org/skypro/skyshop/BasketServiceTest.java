package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void shouldThrowIllegalArgumentExceptionWhenAddingProductWithNullId() {
        // Arrange
        UUID nullId = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> basketService.addProductToBasket(nullId));
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenAddingProductWithEmptyTitle() {
        // Arrange
        UUID existingProductId = UUID.randomUUID();

        // Act
        basketService.addProductToBasket(existingProductId);

        // Assert
        verify(productBasket).addProduct(existingProductId);
    }
    @Test
    void shouldReturnEmptyBasketWhenProductBasketIsEmpty() {
        // Arrange
        when(storageService.getUserBasket()).thenReturn(Collections.emptyList());

        // Act
        UserBasket userBasket = basketService.getUserBasket();

        // Assert
        assertTrue(userBasket.isEmpty());
    }
    @Test
    void shouldReturnUserBasketWithProductsWhenProductBasketIsNotEmpty() {
        // Arrange
        UUID existingProductId1 = UUID.randomUUID();
        UUID existingProductId2 = UUID.randomUUID();

        // Mock the behavior of StorageService to return products when requested
        when(StorageService.getProductById(existingProductId1)).thenReturn(new SimpleProduct(UUID.randomUUID(), "Молоко", 80));
        when(StorageService.getProductById(existingProductId2)).thenReturn(new FixPriceProduct(UUID.randomUUID(), "Хлеб"));

        // Add products to the ProductBasket
        Map<UUID, Integer> productsInBasket = new HashMap<>();
        productsInBasket.put(existingProductId1, 2);
        productsInBasket.put(existingProductId2, 1);
        when(productBasket.getAllProducts()).thenReturn(productsInBasket);

        // Act
        UserBasket userBasket = basketService.getUserBasket();

        // Assert
        // Verify that the returned UserBasket contains the expected products and quantities
        assertEquals(2, userBasket.getItems().size());
        assertEquals(existingProductId1, userBasket.getItems());
        assertEquals(1, userBasket.getItems().get(existingProductId1));
        assertEquals(existingProductId2, userBasket.getItems());
        assertEquals(2, userBasket.getItems().get(existingProductId2));

        // Verify that the total price of the basket is calculated correctly
        assertEquals(400, userBasket.getTotal());
    }
}