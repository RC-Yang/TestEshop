package com.example.pojo.entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product_line")
public class ProductLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prod_line_id")
	private Integer prodLineId;
	
	@Column(name="prod_line")
	private String prodLine;
	
	@OneToMany(mappedBy="prodLine", cascade = CascadeType.ALL)
	private List<Product> productList;

	public Integer getProdLineId() {
		return prodLineId;
	}
	public void setProdLineId(Integer prodLineId) {
		this.prodLineId = prodLineId;
	}
	public String getProdLine() {
		return prodLine;
	}
	public void setProdLine(String prodLine) {
		this.prodLine = prodLine;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
}
