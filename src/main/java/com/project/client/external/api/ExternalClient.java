package com.project.client.external.api;

import com.google.gson.Gson;
import com.project.client.content.product.models.Product;
import com.project.client.shared.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ExternalClient {
    private static final String EXTERNAL_CLIENT_URL = "http://localhost:3001/product/";

    public List<String> getSimilarityIds(String id) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(EXTERNAL_CLIENT_URL + id + "/similarids");
        setHttpHeaders(request);
        HttpResponse response = httpClient.execute(request);
        // Get the response body as a String
        String responseBody = EntityUtils.toString(response.getEntity());
        return StringUtils.extractResponseIdRegex(responseBody);
    }

    public Product getProductDetails(String similarityId) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(EXTERNAL_CLIENT_URL + similarityId);
        setHttpHeaders(request);
        HttpResponse response = httpClient.execute(request);

        // Get the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());

        // Create a Gson object
        Gson gson = new Gson();

        // Parse the response body into a Product object
        Product product = gson.fromJson(responseBody, Product.class);
        if (product == null) throw new IOException("Product not found");
        return product;
    }

    /**
     * Method used to set the http headers.
     *
     * @param request HttpGet request.
     */
    private void setHttpHeaders(HttpGet request) {
        request.addHeader("Accept", "*/*");
    }
}
