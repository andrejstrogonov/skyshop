package org.skypro.skyshop.model.product;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SimpleProduct extends Product{
    private final int price;
    /**
     * Конструктор.
     *
     * @param title название продукта
     */
    public SimpleProduct(@NotNull UUID id,@NotNull String title, int price ) {
        super(title);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной или быть равной нулю");
        }
        this.price = price;
        this.id = id;
    }

    @Override
    public @NotNull int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return getTitle() + ": " + getPrice();
    }
}
