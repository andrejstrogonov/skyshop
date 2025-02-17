package org.skypro.skyshop.service;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(UUID productId) {
        super("No product found with id: " + productId);
    }
}
