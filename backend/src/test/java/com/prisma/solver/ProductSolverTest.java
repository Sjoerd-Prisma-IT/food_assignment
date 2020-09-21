package com.prisma.solver;

import com.prisma.helper.ProductLoader;
import com.prisma.model.Product;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductSolverTest {

    @Test
    public void testSolveSimple() {
        final ProductLoader instance = ProductLoader.getInstance();
        final List<Product> products = instance.getProducts();
        // salt
        final ProductSolver productSolver = new ProductSolver(products.toArray(new Product[products.size()]), 15);
        productSolver.solve();
        assertThat(productSolver.getProductResult().size()).isEqualTo(1);
        assertThat(productSolver.getResultWeight()).isEqualTo(15);
        assertThat(productSolver.getSpentMoney()).isEqualTo(15);
    }

    @Test
    public void testSolveMultiple() {
        final ProductLoader instance = ProductLoader.getInstance();
        final List<Product> products = instance.getProducts();
        // salt, potato, beans
        final ProductSolver productSolver = new ProductSolver(products.toArray(new Product[products.size()]), 29);
        productSolver.solve();
        assertThat(productSolver.getProductResult().size()).isEqualTo(3);
        assertThat(productSolver.getResultWeight()).isEqualTo(25);
        assertThat(productSolver.getSpentMoney()).isEqualTo(29);
    }

}
