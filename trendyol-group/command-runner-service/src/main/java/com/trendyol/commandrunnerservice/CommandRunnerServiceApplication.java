package com.trendyol.commandrunnerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.trendyol.common.client")
public class CommandRunnerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommandRunnerServiceApplication.class, args);
    }
}
