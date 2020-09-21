package com.prisma.controller;

import com.prisma.helper.ProductLoader;
import com.prisma.model.Product;
import com.prisma.model.ProductResultDTO;
import com.prisma.solver.ProductSolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin
public class FoodController {

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @PostMapping(value = "/getProductsForAmount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResultDTO> getProductsForAmount(@RequestBody final int amount) {
        final ProductResultDTO productResultDTO = getProducts(amount);
        return new ResponseEntity<>(productResultDTO, HttpStatus.OK);
    }

    private ProductResultDTO getProducts(final int amount) {
        final ProductLoader productLoader = ProductLoader.getInstance();
        final List<Product> allProducts = productLoader.getProducts();
        final ProductSolver productSolver = new ProductSolver(allProducts.toArray(new Product[allProducts.size()]), amount);
        productSolver.solve();
        return new ProductResultDTO(productSolver.getProductResult(), productSolver.getSpentMoney(), productSolver.getResultWeight());
    }
}
