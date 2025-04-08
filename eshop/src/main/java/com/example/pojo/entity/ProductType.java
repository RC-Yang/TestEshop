package com.example.pojo.entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product_type")
public class ProductType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prod_type_id")
	private Integer prodTypeId;
	@Column(name="prod_type")
	private String prodType;
	@OneToMany(mappedBy="prodType", cascade = CascadeType.ALL)
	private List<Product> productList;
	public Integer getProdTypeId() {
		return prodTypeId;
	}
	public void setProdTypeId(Integer prodTypeId) {
		this.prodTypeId = prodTypeId;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
}
