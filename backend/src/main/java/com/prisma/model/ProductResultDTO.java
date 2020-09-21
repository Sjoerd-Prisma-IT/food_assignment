package com.prisma.model;

import java.util.List;

public class ProductResultDTO {

    private List<Product> products;
    private int amount;
    private int weight;

    public ProductResultDTO(final List<Product> products, final int amount, final int weight) {
        this.products = products;
        this.amount = amount;
        this.weight = weight;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getAmount() {
        return amount;
    }

    public int getWeight() {
        return weight;
    }
}
