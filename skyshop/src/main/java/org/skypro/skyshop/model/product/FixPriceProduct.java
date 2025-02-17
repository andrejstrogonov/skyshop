package org.skypro.skyshop.model.product;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class FixPriceProduct extends Product{
    private final static int FIX_PRICE = 100;
    private final UUID id;
    /**
     * Конструктор.
     *
     * @param title название продукта
     */
    public FixPriceProduct(@NotNull UUID id,@NotNull String title) {
        super(id,title);
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
