package com.cristi.oms;

import java.util.List;

public class Book extends Media {
    private List<String> authors;

    public Book(String title, String category, double cost, List<String> authors) {
        super(title, category, cost);
        setAuthors(authors);
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException("Authors list cannot be null or empty.");
        }
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", cost=" + getCost() +
                ", authors=" + authors +
                '}';
    }
}