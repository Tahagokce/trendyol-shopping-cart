package com.trendyol.shoppingcartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.trendyol", "com.redis.om"})
@EntityScan(value = "com.trendyol.entity")
@ComponentScan("com.trendyol")
@EnableRedisRepositories
@EnableDiscoveryClient
public class ShoppingCartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartServiceApplication.class, args);
    }
}
