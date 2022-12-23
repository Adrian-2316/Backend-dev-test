package com.project.client.content.product.service.ports.in;

import com.project.client.content.product.models.Product;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ProductPort {

    List<Product> getBySimilarity(String id) throws IOException, ExecutionException, InterruptedException;

    List<Product> getFromRedis(String id);

    void saveToRedis(String id, List<Product> products);

    int getExFromRedis(String id);

    void setExToRedis(String id, int exception);
}
