package com.idrbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BankConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankConsumerApplication.class, args);
	}

}
