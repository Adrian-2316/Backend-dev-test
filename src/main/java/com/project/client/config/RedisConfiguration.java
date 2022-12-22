package com.project.client.config;

import com.project.client.content.product.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
  @Bean
  public RedisTemplate<Long, Product> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<Long, Product> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    return template;
  }
}
