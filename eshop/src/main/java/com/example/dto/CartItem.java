package com.example.dto;

public class CartItem {
	private String prodId;
	private String quantity;
	 private Boolean selected;

	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
