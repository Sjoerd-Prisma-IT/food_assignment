package com.prisma.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Product {

    @JsonProperty
    private String name;
    @JsonProperty
    private int quantity;
    @JsonProperty
    private int price;

    public Product() {
    }

    public Product(final String name, final int quantity, final int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity &&
                price == product.price &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price);
    }
}
