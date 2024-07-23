package com.cristi.oms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library<T> implements Serializable {
    private List<T> items;

    public Library() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        this.items.add(item);
    }

    public void removeItem(T item) {
        this.items.remove(item);
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Library{" +
                "items=" + items +
                '}';
    }
}