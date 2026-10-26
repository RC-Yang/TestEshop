package com.example.action;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.pojo.entity.UserCart;
import com.example.service.CartService;

public class CartAction extends BaseAction {

	@Autowired
	private CartService cartservice;

	private String message;

	private List<Object[]> cartList;

	public String addToCart() {
		HttpServletRequest request = getRequest();
		String userId = request.getParameter("userId");
		String prodId = request.getParameter("prodId");
		String quantity = request.getParameter("quantity");

		int result = cartservice.addToCart(Integer.parseInt(userId), Integer.parseInt(prodId),
				Integer.parseInt(quantity));

		if (result > 0) {
			message = "商品成功加入購物車！";
		}

		return SUCCESS;
	}

	public String removeFromCart() {

		HttpServletRequest request = getRequest();

		String userId = request.getParameter("userId");
		String[] prodIds = getRequest().getParameterValues("prodIds");

		List<Integer> ids = Arrays.stream(prodIds).map(Integer::valueOf).collect(Collectors.toList());

		int result = cartservice.removeFromCart(Integer.parseInt(userId), ids);

		if (result > 0) {
			message = "商品成功從購物車移除！";
		} else {
			message = "找不到指定商品！";
		}

		return SUCCESS;
	}

	public String queryCart() {
		HttpServletRequest request = getRequest();
		String loginId = request.getParameter("loginId");
		cartList = cartservice.queryCart(loginId);
		return SUCCESS;
	}

	public CartService getCartservice() {
		return cartservice;
	}

	public void setCartservice(CartService cartservice) {
		this.cartservice = cartservice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object[]> getCartList() {
		return cartList;
	}

	public void setCartList(List<Object[]> cartList) {
		this.cartList = cartList;
	}
}
