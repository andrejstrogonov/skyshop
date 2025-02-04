package org.skypro.skyshop.model.product;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class FixPriceProduct extends Product{
    private final static int FIX_PRICE = 100;
    /**
     * Конструктор.
     *
     * @param title название продукта
     */
    public FixPriceProduct(@NotNull String title, @NotNull UUID id) {
        super(title);
        this.id = id;
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return   getTitle() + ": Фиксированная цена " + FIX_PRICE;
    }
    @Override
    public boolean isSpecial() {
        return true;
    }
}
