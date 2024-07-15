package com.cristi.oms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    private final List<Media> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(Media item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        items.add(item);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(Media::getCost).sum();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Media item : items) {
            builder.append(item.getTitle()).append(": $").append(item.getCost()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return items.equals(order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
