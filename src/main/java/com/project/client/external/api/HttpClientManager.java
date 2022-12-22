package com.project.client.external.api;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HttpClientManager {

  private static final int IDLE_CONNECTION_TIMEOUT = 30; // seconds
  private static final int POOL_SIZE = 100;
  private static final int MAX_CONNECTIONS_PER_ROUTE = 20;

  private final CloseableHttpClient httpClient;
  private final ScheduledExecutorService executor;
  private final PoolingHttpClientConnectionManager connectionManager;

  public HttpClientManager() {
    // Create a connection manager with a pool of connections
    connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(POOL_SIZE);
    connectionManager.setDefaultMaxPerRoute(MAX_CONNECTIONS_PER_ROUTE);

    // Create an HttpClient with the connection manager
    httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

    // Close idle connections every 30 seconds
    executor = Executors.newSingleThreadScheduledExecutor();
    executor.scheduleAtFixedRate(
        () -> {
          connectionManager.closeIdleConnections(IDLE_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        },
        0,
        IDLE_CONNECTION_TIMEOUT,
        TimeUnit.SECONDS);
  }

  public void close() {
    // Close the connection manager and shutdown the executor
    connectionManager.close();
    executor.shutdown();
  }
}
