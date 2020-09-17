package com.prisma.controller;

import com.prisma.helper.ProductHelper;
import com.prisma.model.AmountDTO;
import com.prisma.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FoodController {

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @PostMapping(value = "/getProductsForAmount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsForAmount(@RequestBody final AmountDTO amount) {
        final List<Product> products = getProducts(amount);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    private List<Product> getProducts(final AmountDTO amountDTO) {
        final ProductHelper productHelper = ProductHelper.getInstance();
        final List<Product> allProducts = productHelper.getProducts();
        final List<Product> selectedProducts = amountDTO.getSelectedProducts();
        final List<Product> remainingProducts = new ArrayList<>(allProducts);
        remainingProducts.removeAll(selectedProducts);

        final int totalCost = amountDTO.getSelectedProducts()
                .stream()
                .map(Product::getPrice)
                .mapToInt(e -> e)
                .sum();

        final int remainingAmount = Integer.parseInt(amountDTO.getAmount()) - totalCost;

        return remainingProducts
                .stream()
                .filter(product -> product.getPrice() <= remainingAmount)
                .collect(Collectors.toList());
    }
}
