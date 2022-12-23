package com.project.client.content.product.adapter.out.persistence;

import com.google.gson.Gson;
import com.project.client.content.product.models.Product;
import com.project.client.content.product.service.ports.out.RedisRepositoryPort;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RedisRepository implements RedisRepositoryPort {
    private static final String PREFIX_KEY = "product:";
    private static final String FIELD_KEY = "object";
    Gson gson = new Gson();
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    JedisPool jedisPool = new JedisPool();

    /**
     * Constructor for the RedisRepository class.
     */
    public RedisRepository() {
        this.poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200); // Maximum number of connections in the pool
        poolConfig.setMaxIdle(10);
        this.jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    /**
     * Method used to save the product ids in redis.
     *
     * @param key key to save the ids in redis.
     */
    public void setExpirationTime(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.expire(PREFIX_KEY + key, 13600);
        jedis.close();
    }

    /**
     * Method used to save the product details in redis.
     *
     * @param key      key to save the details in redis.
     * @param products list of products to save in redis.
     */
    @Override
    public void saveToRedis(String key, List<Product> products) {
        Jedis jedis = jedisPool.getResource();
        for (Product product : products) {
            jedis.hset(PREFIX_KEY + key, FIELD_KEY + product.getId(), gson.toJson(product));
            setExpirationTime(product.getId());
        }
        jedis.close();
    }

    @Override
    public void setExToRedis(String key, int exception) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(PREFIX_KEY + key +":exception", String.valueOf(exception));
        setExpirationTime(key);
        jedis.close();
    }

    /**
     * Method used to retrieve the product details from redis.
     *
     * @param id id of the product to retrieve the details from redis.
     * @return list of products retrieved from redis.
     */
    @Override
    public List<Product> getFromRedis(String id) {
        Jedis jedis = jedisPool.getResource();
        // Get the keys for all the fields in the hash
        Map<String, String> stringStringMap = jedis.hgetAll(PREFIX_KEY + id);
        List<Product> products = new ArrayList<>();
        for (String fieldKey : stringStringMap.values()) {
            Product product = gson.fromJson(fieldKey, Product.class);
            products.add(product);
        }
        jedis.close();
        return products;
    }

    @Override
    public Integer getExFromRedis(String id) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(PREFIX_KEY + id+":exception");
        if (value == null) {
            return 0;
        }
        jedis.close();
        return Integer.parseInt(value);
    }
}
