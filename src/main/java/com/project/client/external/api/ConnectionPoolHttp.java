package com.project.client.external.api;

import com.google.gson.Gson;
import com.project.client.content.product.models.Product;
import com.project.client.shared.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConnectionPoolHttp {

  private static final String HOST = "http://localhost:3001/product/";
  CloseableHttpClient httpClient;
  Jedis jedis = new Jedis("localhost");

  public ConnectionPoolHttp() {
    HttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
    this.httpClient = HttpClients.custom().setConnectionManager(poolingConnManager).build();
  }

  /**
   * Method used to retrieve the product id list from the similarity products found.
   *
   * @param id the product id.
   * @return the list of product ids.
   * @throws IOException if the connection fails.
   */
  public List<String> getProductsBySimilarity(String id) throws IOException {
    CloseableHttpResponse execute = httpClient.execute(new HttpGet(HOST + id + "/similarids"));
    if (execute.getStatusLine().getStatusCode() == 404) {
      throw new IOException("Product not found");
    }
    HttpEntity entity = execute.getEntity();
    String responseBody = EntityUtils.toString(entity);
    return StringUtils.extractResponseIdRegex(responseBody);
  }

  /**
   * Method used to get the products by similarity using a thread pool to execute the requests.
   *
   * @param ids list of ids to be used to get the products by similarity
   * @return list of products
   * @throws IOException In case id is not found.
   * @throws ExecutionException In case something goes wrong with the execution.
   * @throws InterruptedException In case something goes wrong with the thread pool.
   */
  public List<Product> getProductDetails(List<String> ids) throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    List<Future<CloseableHttpResponse>> futures = generateAsyncTasks(ids, executor);
    List<Product> products = getFutureResponses(futures);
    executor.shutdown();
    return products;
  }

  /**
   * Method used to get the information of Future responses in the thread pool.
   *
   * @param futures list of futures to be used to get the products by similarity.
   * @return list of products.
   * @throws InterruptedException In case something goes wrong with the thread pool.
   * @throws ExecutionException In case something goes wrong with the execution.
   * @throws IOException In case id is not found.
   */
  @NotNull
  @Cacheable(value = "product", key = "#productId")
  private List<Product> getFutureResponses(List<Future<CloseableHttpResponse>> futures)
      throws IOException, ExecutionException, InterruptedException {
    List<Product> products = new ArrayList<>();
    Gson gson = new Gson();
    for (Future<CloseableHttpResponse> future : futures) {
      CloseableHttpResponse response = future.get();
      if (response.getStatusLine().getStatusCode() == 404)
        throw new IOException("Product not found");
      HttpEntity entity = response.getEntity();
      String json = EntityUtils.toString(entity);
      Product product = gson.fromJson(json, Product.class);
      if (product == null) throw new HttpResponseException(500, "Error parsing request");
      products.add(product);
    }
    return products;
  }

  /**
   * Method used to generate the async tasks by the thread pool.
   *
   * @param ids list of ids to be used to get the products by similarity.
   * @param executor the thread pool executor.
   * @return list of futures.
   */
  @NotNull
  private List<Future<CloseableHttpResponse>> generateAsyncTasks(
      List<String> ids, ExecutorService executor) {
    List<Future<CloseableHttpResponse>> futures = new ArrayList<>();
    for (String id : ids) {
      HttpGet request = new HttpGet(HOST + id);
      Future<CloseableHttpResponse> future = executor.submit(() -> httpClient.execute(request));
      futures.add(future);
    }
    return futures;
  }
}
