package com.project.client.content.product.service.ports.out;

import com.project.client.content.product.models.Product;

import java.util.List;

public interface RedisRepositoryPort {

  void saveToRedis(String key, List<Product> productDetails);
  void setExToRedis(String key, int exception);

  List<Product> getFromRedis(String id);
  Integer getExFromRedis(String id);
}
