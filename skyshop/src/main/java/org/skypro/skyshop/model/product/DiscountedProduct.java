package org.skypro.skyshop.model.product;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;

    /**
     * Конструктор.
     *
     * @param title название продукта
     */
    public DiscountedProduct(@NotNull UUID id,@NotNull String title, int basePrice, int discount ) {
        super(title);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена не может быть отрицательной или быть равной нулю");
        }
        this.basePrice = basePrice;
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100%");
        }
        this.discount = discount;
        this.id = id;
    }

    @Override
    public @NotNull int getPrice() {
        return basePrice - (int)((double)(basePrice * discount) / 100.0);
    }

    @Override
    public String toString() {
        return getTitle() + ": " + getPrice() + " (скидка "+ discount + "%)";
    }
    @Override
    public boolean isSpecial() {
        return true;
    }
}
