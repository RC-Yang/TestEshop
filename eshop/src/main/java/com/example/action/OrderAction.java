package com.example.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.constant.ConstantName;
import com.example.pojo.entity.Order;
import com.example.pojo.entity.User;
import com.example.service.OrderService;

public class OrderAction extends BaseAction{

	@Autowired
	private OrderService orderService;
	
	List<Order> orderList;
	
	public String queryOrderList() {
		User user = (User)getSession().getAttribute(ConstantName.SESSION_USER);
		orderList = orderService.queryOrder(user.getLoginId());
		
		return SUCCESS;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}
