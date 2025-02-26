package org.skypro.skyshop.model.basket;

import java.util.*;

public final class UserBasket {
    private final List<BasketItem> basketItems;
    private final int total;

    public UserBasket(List<BasketItem> basketItems, int total) {
        this.basketItems = Collections.unmodifiableList(basketItems);
        this.total = total;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return total == that.total && basketItems.equals(that.basketItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketItems, total);
    }

    @Override
    public String toString() {
        return "UserBasket{" +
                "basketItems=" + basketItems +
                ", total=" + total +
                '}';
    }

    public boolean isEmpty() {
        return false;
    }

    public Map<UUID, Integer> getItems() {
        Map<UUID, Integer> items = new HashMap<>();
        for (BasketItem basketItem : basketItems) {
            items.put(basketItem.getProduct().getId(), basketItem.getQuantity());
        }
        return items;
    }
}
