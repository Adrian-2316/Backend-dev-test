package com.project.client.content.product.service;

import com.project.client.content.product.adapter.out.persistence.RedisRepository;
import com.project.client.content.product.models.Product;
import com.project.client.content.product.service.ports.in.ProductPort;
import com.project.client.content.product.service.ports.out.RedisRepositoryPort;
import com.project.client.external.api.ConnectionPoolHttp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductPort {
  private final RedisRepositoryPort redisRepositoryPort = new RedisRepository();

  /**
   * This method retrieves a list of products and their details based on their similarity to a
   * particular product. It checks a cache (Redis) for the product's similar products list, and if
   * it doesn't exist, it retrieves it from an external API and saves it to the cache. It then
   * retrieves the product details from the cache or the external API and returns the list of
   * product details. If it retrieved the product details from the external API, it also saves them
   * to the cache.
   *
   * @param id The id of the product to retrieve similar products for.
   * @return A list of products and their details.
   * @throws Exception If there was an error retrieving the product details.
   */
  @Override
  public List<Product> getBySimilarity(String id) throws Exception {
    ConnectionPoolHttp connectionPoolHttp = new ConnectionPoolHttp();
    List<Product> productDetails = new ArrayList<>();
    List<String> similarityIds;

    similarityIds = connectionPoolHttp.getProductsBySimilarity(id);
    if (similarityIds.isEmpty()) return productDetails;
    productDetails = connectionPoolHttp.getProductDetails(similarityIds);

    return productDetails;
  }

  /**
   * This method retrieves a list of products from the cache.
   * @param id The id of the product to retrieve similar products for.
   * @return  A list of products.
   */
  @Override
  public List<Product> getFromRedis(String id) {
    return redisRepositoryPort.getFromRedis(id);
  }

  /**
   * This method saves a list of products to the cache.
   * @param id The id of the product to retrieve similar products for.
   * @param products A list of products.
   */
  @Override
  public void saveToRedis(String id, List<Product> products) {
     redisRepositoryPort.saveToRedis(id, products);
  }
}
