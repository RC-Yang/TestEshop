package com.example.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_detail")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ord_num_id")
	private Integer ordNumId;
	@Column(name="ord_num")
	private String ordNum;
	
	@OneToOne
	@JoinColumn(name="prod_id")
	private Product product;
	@Column(name="ord_qty")
	private Integer ordQty;
	@Column(name="amount")
	private Integer amount;
	@ManyToOne
	@JoinColumn(name="cust_id")
	private Customer customer;
	@Column(name="state")
	private String state;

	public Integer getOrdNumId() {
		return ordNumId;
	}
	public void setOrdNumId(Integer ordNumId) {
		this.ordNumId = ordNumId;
	}
	public String getOrdNum() {
		return ordNum;
	}
	public void setOrdNum(String ordNum) {
		this.ordNum = ordNum;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(Integer ordQty) {
		this.ordQty = ordQty;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
