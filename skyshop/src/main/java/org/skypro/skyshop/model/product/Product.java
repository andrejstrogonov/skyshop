package org.skypro.skyshop.model.product;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Product {
    /**
     * Название продукта.
     */
    @NotNull
    private final String title;


    /**
     * Конструктор.
     *
     * @param title название продукта
     */
    public Product(@NotNull String title) {
        if (title.isBlank()) {
            throw new IllegalArgumentException("Наименование продукта не может быть пустым");
        }
        this.title = title;
    }

    /**
     * Получить название продукта.
     *
     * @return название продукта.
     */
    @NotNull
    public String getTitle() {
        return title;
    }

    @NotNull
    public abstract int getPrice();

    @Override
    public String toString() {
        return title;
    }

    public boolean isSpecial(){
        return false;
    }
    @NotNull
    public String getSearchableName() {
        return this.getClass().getSimpleName() + "-" + SEARCHABLE_CONTENT_KIND + "-" + this.hashCode();
    }

    @Override
    public @NotNull String getSearchableTerm() {
        return toString();
    }

    public static final String SEARCHABLE_CONTENT_KIND = "PRODUCT";

    @Override
    public @NotNull String getSearchableContentKind() {
        return SEARCHABLE_CONTENT_KIND;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
