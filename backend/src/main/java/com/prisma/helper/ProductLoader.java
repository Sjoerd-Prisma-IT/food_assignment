package com.prisma.helper;

import com.prisma.model.Product;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ProductLoader {

    private final List<Product> products = new ArrayList<>();
    private static ProductLoader instance;

    private ProductLoader() {
        loadProducts();
    }

    public static ProductLoader getInstance() {
        if (isNull(instance)) {
            instance = new ProductLoader();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    private void loadProducts() {
        InputStream inputStream;
        try {
            inputStream = new ClassPathResource("data/products.csv").getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String csvLine;
            reader.readLine();
            while ((csvLine = reader.readLine()) != null) {
                final String[] values = csvLine.split(",");
                products.add(new Product(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
