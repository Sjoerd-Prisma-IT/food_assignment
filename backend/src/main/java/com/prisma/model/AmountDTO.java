package com.prisma.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AmountDTO {

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("selectedProducts")
    private List<Product> selectedProducts;

    public AmountDTO() {
    }

    public AmountDTO(final String amount, final List<Product> selectedProducts) {
        this.amount = amount;
        this.selectedProducts = selectedProducts;
    }

    public String getAmount() {
        return amount;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    @Override
    public String toString() {
        return "AmountDTO{" +
                "amount='" + amount + '\'' +
                ", selectedProducts=" + selectedProducts +
                '}';
    }
}
