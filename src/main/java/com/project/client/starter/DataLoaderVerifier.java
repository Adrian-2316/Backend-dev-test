package com.project.client.starter;


import com.project.client.content.product.adapter.in.rest.ProductController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class DataLoaderVerifier {
    @Autowired
    ProductController productController;

    public void initializeCommonSearches() throws IOException, ExecutionException, InterruptedException {
        log.warn("Initializing common searches, this could take a minute...");
        for (String s : Arrays.asList("1", "2", "3", "4", "5")) {
            try {
                productController.getBySimilarity(s);
            } catch (Exception ignored) {
                // Ignored exception because it is expected.
            }
        }
        log.info("Finalize common searches");

    }
}
