package com.infy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
public class PaymentMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMSApplication.class, args);
	}

}
