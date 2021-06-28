package com.mabaya.test.sagi.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepositoryCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) {

        List<String> categories = new ArrayList<>(
                Arrays.asList("smartphone", "shoes", "t-shirts", "food"));

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            int random = (int) (Math.random() * 4);

            Product product = new Product(10000 + i,
                    "Product " + i, categories.get(random), i + 1.0);

            products.add(product);
        }

        productRepository.saveAll(products);
    }
}
