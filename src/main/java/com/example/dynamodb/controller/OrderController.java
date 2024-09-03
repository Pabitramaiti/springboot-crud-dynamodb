package com.example.dynamodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dynamodb.model.Order;
import com.example.dynamodb.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/saveorder")
    public Order saveOrder(@RequestBody Order order){
        return service.saveOrder(order);
    }
    
    @PostMapping("/saveorders")
    public List<Order> saveOrder(@RequestBody List<Order> orders){
        return service.saveOrders(orders);
    }
    
    /*@PostMapping("/updateorder")
    public String updateOrder(@RequestBody Order order){
        return service.updateOrder(order);
    }*/

    @GetMapping("/getallorders")
    public List<Order> getAllOrders(){
        return service.getAllOrders();
    }

    @GetMapping("/getorder/{id}")
    public Order getOrderById(@PathVariable String id){
        return service.getOrderById(id);
    }
    
    @DeleteMapping("/deleteorder/{id}")
    public String deleteOrderById(@PathVariable String id){
    	try {
			service.deleteOrderById(id);
			return "Order with Id : '" + id + "' is removed successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
    }
}
