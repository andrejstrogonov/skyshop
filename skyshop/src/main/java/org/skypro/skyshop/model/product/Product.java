package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public abstract class Product {
    /**
     * Название продукта.
     */
    @NotNull
    private final String title;

    @NotNull
    private final UUID id;

    public @NotNull UUID getId() {
        return id;
    }

    /**
     * Конструктор.
     *
     * @param title название продукта
     */
    public Product( @NotNull UUID id,@NotNull String title) {
        if (title.isBlank()) {
            throw new IllegalArgumentException("Наименование продукта не может быть пустым");
        }
        this.title = title;
        this.id = id;
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

    @JsonIgnore
    @Override
    public @NotNull String getSearchableTerm() {
        return toString();
    }

    public static final String SEARCHABLE_CONTENT_KIND = "PRODUCT";

    @JsonIgnore
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

    public Product orElseThrow(Object productNotFound) {
        if (this == null) {
            throw new IllegalArgumentException(productNotFound.toString());
        }
        return this;
    }
}
