package com.example.dynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
public class AwsDynamodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsDynamodbApplication.class, args);
	}
}
