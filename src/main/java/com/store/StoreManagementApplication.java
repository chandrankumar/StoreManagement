package com.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("com.store")
@EntityScan(basePackages= {"com.store.entities"})
@SpringBootApplication
public class StoreManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreManagementApplication.class, args);
	}

}
