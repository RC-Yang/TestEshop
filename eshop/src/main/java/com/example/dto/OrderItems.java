package com.example.dto;

import java.util.List;

import com.example.pojo.entity.Order;

public class OrderItems {
	private String ordNum; 
    private List<Order> orders;
	public OrderItems(String ordNum, List<Order> orders) {
		super();
		this.ordNum = ordNum;
		this.orders = orders;
	}
	public String getOrdNum() {
		return ordNum;
	}
	public void setOrdNum(String ordNum) {
		this.ordNum = ordNum;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
