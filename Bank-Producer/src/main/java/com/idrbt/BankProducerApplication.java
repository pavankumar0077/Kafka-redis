package com.idrbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = "com.idrbt.repository")
public class BankProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankProducerApplication.class, args);
    }
}
