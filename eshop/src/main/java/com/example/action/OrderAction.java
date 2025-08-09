package com.example.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.constant.ConstantName;
import com.example.dto.OrderItems;
import com.example.pojo.entity.Order;
import com.example.pojo.entity.User;
import com.example.service.OrderService;
import org.springframework.data.domain.Pageable;

public class OrderAction extends BaseAction{

	@Autowired
	private OrderService orderService;
	
	Page<Order> orderPageList;
	//List<Order> orderList;
	List<OrderItems> orderGroupList;
	private String message;

	private List<Object> stateOrderNumList;
	
	private int page;
	private int size;
	
//	public String queryOrderList() {
//		User user = (User)getSession().getAttribute(ConstantName.SESSION_USER);
//		
//		if(user.getUserType()==1) {
//			orderList = orderService.queryOrder(user.getLoginId());
//		}else {
//			orderGroupList = orderService.queryAllOrder();
//		}		
//		return SUCCESS;
//	}
	
	public String queryOrderList() {
		User user = (User)getSession().getAttribute(ConstantName.SESSION_USER);
		
		if(user.getUserType()==1) {
			Pageable pageable = PageRequest.of(page-1, size, Sort.by("ordNumId").descending());
			orderPageList = orderService.queryOrder(user.getLoginId(),pageable);
		}else {
			orderGroupList = orderService.queryAllOrder();
		}		
		return SUCCESS;
	}
	
	 public String queryAllOrder(){
		orderGroupList = orderService.queryAllOrder();
		stateOrderNumList=orderService.findStateAndOrderNum();
		getRequest().setAttribute("stateOrderNumList", stateOrderNumList);
		 
		return SUCCESS;
	 }
	 
	 public String updateOrderStateByOrdNum() {
		 String ordNum = getRequest().getParameter("ordNum");
		 String state = getRequest().getParameter("state");
		 
		 int result = orderService.updateOrderStateByOrdNum(state, ordNum);
		 
		 if(result>0) {
			 message="訂單已成功出貨！"; 

			 return SUCCESS;
		 }else {
			 return "";
		 }
	 }

//	public List<Order> getOrderList() {
//		return orderList;
//	}
//
//	public void setOrderList(List<Order> orderList) {
//		this.orderList = orderList;
//	}

	public List<OrderItems> getOrderGroupList() {
		return orderGroupList;
	}

	public Page<Order> getOrderPageList() {
		return orderPageList;
	}

	public void setOrderPageList(Page<Order> orderPageList) {
		this.orderPageList = orderPageList;
	}

	public void setOrderGroupList(List<OrderItems> orderGroupList) {
		this.orderGroupList = orderGroupList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Object> getStateOrderNumList() {
		return stateOrderNumList;
	}

	public void setStateOrderNumList(List<Object> stateOrderNumList) {
		this.stateOrderNumList = stateOrderNumList;
	}
}
