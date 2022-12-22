package com.project.client.content.product.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RedisRepository.class})
@ExtendWith(SpringExtension.class)
class RedisRepositoryTest {
    @Autowired
    private RedisRepository redisRepository;

    /**
     * Method under test: {@link RedisRepository#setExpirationTime(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetExpirationTime() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool
        //       at redis.clients.util.Pool.getResource(Pool.java:53)
        //       at redis.clients.jedis.JedisPool.getResource(JedisPool.java:99)
        //       at com.project.client.content.product.adapter.out.persistence.RedisRepository.setExpirationTime(RedisRepository.java:39)
        //   In order to prevent setExpirationTime(String)
        //   from throwing JedisConnectionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   setExpirationTime(String).
        //   See https://diff.blue/R013 to resolve this issue.

        redisRepository.setExpirationTime("Key");
    }

    /**
     * Method under test: {@link RedisRepository#saveToRedis(String, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveToRedis() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool
        //       at redis.clients.util.Pool.getResource(Pool.java:53)
        //       at redis.clients.jedis.JedisPool.getResource(JedisPool.java:99)
        //       at com.project.client.content.product.adapter.out.persistence.RedisRepository.saveToRedis(RedisRepository.java:51)
        //   In order to prevent saveToRedis(String, List)
        //   from throwing JedisConnectionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveToRedis(String, List).
        //   See https://diff.blue/R013 to resolve this issue.

        redisRepository.saveToRedis("Key", new ArrayList<>());
    }

    /**
     * Method under test: {@link RedisRepository#getFromRedis(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetFromRedis() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool
        //       at redis.clients.util.Pool.getResource(Pool.java:53)
        //       at redis.clients.jedis.JedisPool.getResource(JedisPool.java:99)
        //       at com.project.client.content.product.adapter.out.persistence.RedisRepository.getFromRedis(RedisRepository.java:66)
        //   In order to prevent getFromRedis(String)
        //   from throwing JedisConnectionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getFromRedis(String).
        //   See https://diff.blue/R013 to resolve this issue.

        redisRepository.getFromRedis("42");
    }
}

