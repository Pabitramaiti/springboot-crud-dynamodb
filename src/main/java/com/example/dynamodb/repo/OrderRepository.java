package com.example.dynamodb.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.dynamodb.model.Order;

@Repository
public class OrderRepository {
	
	@Autowired
    private DynamoDBMapper dynamoDBMapper;

	public Order save(Order order) {
        dynamoDBMapper.save(order);
        return order;
    }
	
	public List<Order> saveAll(List<Order> orders) {
        dynamoDBMapper.save(orders);
        return orders;
    }
	
	public List<Order> findAll(){
		return dynamoDBMapper.scan(Order.class, new DynamoDBScanExpression());
	}
	
    public Order findById(String orderId) {
        return dynamoDBMapper.load(Order.class, orderId);
    }

    public Order findByName(String name) {
    	return dynamoDBMapper.load(Order.class, name);
    }

    public String delete(String orderId) {
    	Order ord = dynamoDBMapper.load(Order.class, orderId);
        dynamoDBMapper.delete(ord);
        return "Order Deleted!";
    }

    public String update(String orderId, Order order) {
        dynamoDBMapper.save(order,
                new DynamoDBSaveExpression()
        .withExpectedEntry("employeeId",
                new ExpectedAttributeValue(
                        new AttributeValue().withS(orderId)
                )));
        return orderId;
    }
    
    
}
