package com.example.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.constant.ConstantName;
import com.example.dto.OrderItems;
import com.example.pojo.entity.Order;
import com.example.pojo.entity.User;
import com.example.service.OrderService;

public class OrderAction extends BaseAction{

	@Autowired
	private OrderService orderService;
	
	List<Order> orderList;
	List<OrderItems> orderGroupList;
	private String message;
	
	public String queryOrderList() {
		User user = (User)getSession().getAttribute(ConstantName.SESSION_USER);
		
		if(user.getUserType()==1) {
			orderList = orderService.queryOrder(user.getLoginId());
		}else {
			orderGroupList = orderService.queryAllOrder();
		}		
		return SUCCESS;
	}
	
	 public String queryAllOrder(){
		 orderGroupList = orderService.queryAllOrder();
		 
		 return SUCCESS;
	 }
	 
	 public String updateOrderStateByOrdNum() {
		 String ordNum = getRequest().getParameter("ordNum");
		 String state = getRequest().getParameter("state");
		 
		 int result = orderService.updateOrderStateByOrdNum(state, ordNum);
		 
		 if(result>0) {
			 message="訂單已成功出貨！";
			 return SUCCESS;
			 //上面的SUCCESS，搭配xml設定，表示要將response body內容，包裝為json格式
			 //message="訂單已成功出貨！"就成為json格式字串內容
		 }else {
			 return "";
		 }
	 }

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<OrderItems> getOrderGroupList() {
		return orderGroupList;
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
}
