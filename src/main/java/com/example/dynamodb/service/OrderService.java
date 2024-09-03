package com.example.dynamodb.service;

import java.util.List;

import com.example.dynamodb.model.Order;

public interface OrderService {

	public Order saveOrder(Order order);

	public List<Order> saveOrders(List<Order> orders);

	public List<Order> getAllOrders();

	public Order getOrderById(String id);

	public void deleteOrderById(String id);

	//public String updateOrder(Order order);

}
