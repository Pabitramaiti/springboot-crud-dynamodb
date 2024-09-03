package com.example.dynamodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
//@RequiredArgsConstructor
//@EnableDynamoDBRepositories(basePackages = "")
//@AllArgsConstructor
//@NoArgsConstructor
public class DynamoDBConfig {

	@Autowired
	private AwsProperties awsProperties;

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB());
		return mapper;
	}

	//@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		//AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		//return client;
		System.out.println("Endpoint=====>  " + awsProperties.getEndpoint() + "  Region=====>  " + awsProperties.getRegion().toString());
		AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsProperties.getEndpoint(), awsProperties.getRegion().toString()))
        	.withCredentials(awsCredentialsProvider()).build();

		return amazonDynamoDB;
	}
	
	/*public AmazonDynamoDB amazonDynamoDB_NA() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsProperties.getEndpoint(),
						awsProperties.getRegion()))
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey())))
				.build();
	}*/
	
	//@Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
    	System.out.println("AccessKey=====>  " + awsProperties.getAccessKey() + "  SecretKey=====> " + awsProperties.getSecretKey() );
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey()));
    }

}
