package com.project.client.content.product.adapter.in.rest;

import com.project.client.content.product.models.Product;
import com.project.client.content.product.service.ports.in.ProductPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Products", description = "Products CRUD operations")
@RequestMapping("product")
@Slf4j
public class ProductController {
  @Autowired private ProductPort productPort;

  @Operation(summary = "List of similar products to a given one ordered by similarity.")
  @GetMapping("{productId}/similar")
  @Async
  public List<Product> getBySimilarity(@PathVariable("productId") String id) throws Exception {
    List<Product> fromRedis = productPort.getFromRedis(id);
    if (fromRedis != null && !fromRedis.isEmpty()) return fromRedis;
    List<Product> products = productPort.getBySimilarity(id);
    productPort.saveToRedis(id, products);
    return products;
  }
}
