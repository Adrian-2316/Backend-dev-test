package com.project.client.content.product.service.ports.in;

import com.project.client.content.product.models.Product;

import java.util.List;

public interface ProductPort {

  List<Product> getBySimilarity(String id) throws Exception;

  List<Product> getFromRedis(String id);

  void saveToRedis(String id, List<Product> products);
}
