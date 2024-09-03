package com.example.dynamodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AwsProperties {

	@Value ("${amazon.dynamodb.region}")
	//public Region region;
	public String region;
	@Value ("${amazon.dynamodb.endpoint}")
	public String endpoint;
	@Value ("${amazon.dynamodb.access-key}")
	public String accessKey;
	@Value ("${amazon.dynamodb.secret-key}")
	public String secretKey;
	@Value ("${amazon.dynamodb.timeout}")
	public Integer timeout;
	@Value ("${amazon.dynamodb.retry.maxAttempts}")
	public Integer retryMaxAttempts;
	@Value ("${amazon.dynamodb.retry.backoffSeconds}")
	private Integer retryBackoffSeconds;
	//private Integer httpClientAcquireTimeout;
	//private Integer httpClientMaxConnections;
	//private Integer stsTokenDuration;
}
