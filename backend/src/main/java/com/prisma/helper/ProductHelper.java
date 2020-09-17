package com.prisma.helper;

import com.prisma.model.Product;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ProductHelper {

    private final List<Product> products = new ArrayList<>();
    private static ProductHelper instance;

    private ProductHelper() {
        loadProducts();
    }

    public static ProductHelper getInstance() {
        if (isNull(instance)) {
            instance = new ProductHelper();
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
