package com.project.client.content.product.adapter.in.rest;

import com.project.client.content.product.adapter.out.persistence.RedisRepository;
import com.project.client.content.product.models.Product;
import com.project.client.content.product.service.ports.in.ProductPort;
import com.project.client.content.product.service.ports.out.RedisRepositoryPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Tag(name = "Products", description = "Products CRUD operations")
@RequestMapping("product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductPort productPort;
    private final RedisRepositoryPort redisRepositoryPort = new RedisRepository();

    @Operation(summary = "List of similar products to a given one ordered by similarity.")
    @GetMapping("{id}/similar")
    @Cacheable(value = "products", key = "#id")
    public List<Product> getBySimilarity(@PathVariable("id") String id) throws IOException, ExecutionException, InterruptedException {
        int ex = productPort.getExFromRedis(id);
        checkRedisSavedException(ex);
        try {
            List<Product> fromRedis = productPort.getFromRedis(id);
            if (fromRedis != null && !fromRedis.isEmpty()) return fromRedis;
            List<Product> products = productPort.getBySimilarity(id);
            productPort.saveToRedis(id, products);
            return products;
        } catch (HttpResponseException e) {
            productPort.setExToRedis(id, 500);
            throw new HttpResponseException(500, "Internal Server Error");
        } catch (IOException e) {
            productPort.setExToRedis(id, 404);
            throw new IOException("Product not found");
        }
    }

    /**
     * Method used to check if the exception was saved in Redis.
     * @param ex Exception.
     * @throws IOException  Exception.
     */
    private void checkRedisSavedException(int ex) throws IOException {
        if (ex == 404) throw new IOException("Product not found");
        if (ex == 500) throw new HttpResponseException(500, "Internal Server Error");
    }

}
