package com.prisma.solver;

import com.prisma.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductSolver {

    private final Product[] allProducts;
    private final int amount;

    private final List<Product> productResult = new ArrayList<>();
    private int resultWeight = 0;
    private int spentMoney = 0;

    public ProductSolver(final Product[] allProducts, final int amount) {
        this.allProducts = allProducts;
        this.amount = amount;
    }

    public void solve() {
        final int amountOfProducts = allProducts.length;
        final int[][] matrix = new int[amountOfProducts + 1][amount + 1];

        for (int i = 0; i <= amount; i++)
            matrix[0][i] = 0;

        for (int i = 1; i <= amountOfProducts; i++) {
            for (int j = 0; j <= amount; j++) {
                if (allProducts[i - 1].getPrice() > j) {
                    matrix[i][j] = matrix[i - 1][j];
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - allProducts[i - 1].getPrice()]
                            + allProducts[i - 1].getQuantity());
                }
            }
        }

        int res = matrix[amountOfProducts][amount];
        int w = amount;

        for (int i = amountOfProducts; i > 0 && res > 0; i--) {
            if (res != matrix[i - 1][w]) {
                final Product product = allProducts[i - 1];
                productResult.add(product);
                spentMoney += product.getPrice();
                resultWeight += product.getQuantity();
                res -= product.getQuantity();
                w -= product.getPrice();
            }
        }
    }

    public List<Product> getProductResult() {
        return productResult;
    }

    public int getResultWeight() {
        return resultWeight;
    }

    public int getSpentMoney() {
        return spentMoney;
    }
}
