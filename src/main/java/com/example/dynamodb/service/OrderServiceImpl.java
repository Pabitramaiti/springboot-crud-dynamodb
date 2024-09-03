package com.example.dynamodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dynamodb.exception.CustomException;
import com.example.dynamodb.exception.OrderAlreadyExistException;
import com.example.dynamodb.exception.OrderNotFoundException;
import com.example.dynamodb.model.Order;
import com.example.dynamodb.repo.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;

	@Override
	public Order saveOrder(Order order) {
		// return repo.save(order);
		log.info("Order Name :- " +order.getName());
		//Order existingOrder = repository.findById(order.getId()).orElse(null);
		Order existingOrder = repository.findByName(order.getName());
		//log.info("existingOrder :- " + existingOrder.getName());
		if (existingOrder == null) {
			return repository.save(order);
			// return "Order added successfully";
		} else if(existingOrder!=null) {
			log.info("Order '" + order + "' already exists!!");
			throw new OrderAlreadyExistException("Order '" + order + "' already exists!!");
		}
		else {
			throw new CustomException("Internal Server Error!!!!!");
		}
	}

	@Override
	public List<Order> saveOrders(List<Order> orders) {
		// return repo.save(order);
		List<String> ordName = new ArrayList<String>();
		Order existingOrder = null;
		for(Order order : orders) {
			String orderName = order.getName();
			log.info("Order Name :- " + orderName);
			//Order existingOrder = repository.findById(order.getId()).orElse(null);
			existingOrder = repository.findByName(orderName);
			if(existingOrder != null) {
				ordName.add(orderName);
			}
		}
		if (ordName.size() > 0 ) {
			log.info("Order " + ordName + " already exists!!");
			throw new OrderAlreadyExistException("Order " + ordName + " already exists!!");
			//return repository.saveAll(order);
			// return "Order added successfully";
		} else if(ordName.size() == 0 ) {
			log.info("Order added successfully");
			return repository.saveAll(orders);
			//return "Order added successfully";
		}
		else {
			log.info("Internal Server Error!");
			throw new CustomException("Internal Server Error!");
		}
		
	}

	@Override
	public List<Order> getAllOrders() {
		return repository.findAll();
	}

	@Override
	public Order getOrderById(String id) {

		Order ord = repository.findById(id);

		if (ord !=null) {
			return ord;
		} else {
			throw new OrderNotFoundException("Order with Id : " + id + " Not Found");
		}

		// return repo.findById(id).orElseThrow(()-> new OrderNotFoundException("Order
		// with Id : " + id + " Not Found"));
	}

	@Override
	public void deleteOrderById(String id) {
		repository.delete(getOrderById(id).getId());
	}

	/*@Override
	public String updateOrder(Order order) {
		// repo.save(order);

		Order existingOrder = repository.findById(order.getId()).orElse(null);
		if (existingOrder == null)
			throw new OrderNotFoundException("No Such Order exists!!");
		else {
			existingOrder.setName(order.getName());
			existingOrder.setQty(order.getQty());
			existingOrder.setPrice(order.getPrice());
			repository.save(existingOrder);
			return "Record updated Successfully!";
		}
	}*/

	/*
	 * public Order addOrder(Order order){ return repository.save(order); }
	 * 
	 * public List<Order> getOrders(){ return repository.findAll(); }
	 * 
	 * public Order getOrderById(int id){ return repository.findById(id)
	 * .orElseThrow(()->new IllegalArgumentException("Invalid id : "+id)); }
	 * 
	 * public void deleteOrderById(int id) { repository.deleteById(id); }
	 */

}
