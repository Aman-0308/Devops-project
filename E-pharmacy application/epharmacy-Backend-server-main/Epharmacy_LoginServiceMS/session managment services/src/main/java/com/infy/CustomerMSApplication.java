package com.infy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
@EntityScan(basePackages = {"com.infy.entity"})
public class CustomerMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMSApplication.class, args);
	}

}


