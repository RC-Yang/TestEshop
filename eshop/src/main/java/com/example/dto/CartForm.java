package com.example.dto;

import java.util.List;

public class CartForm {
	private List<CartItem> cartItem;

	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}

}
