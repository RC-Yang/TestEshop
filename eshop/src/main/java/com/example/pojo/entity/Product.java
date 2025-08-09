package com.example.pojo.entity;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prod_id")
	private Integer prodId;
	@Column(name="prod_name")
	private String prodName;
	@Column(name="prod_num")
	private String prodNum;
	@Column(name="prod_price")
	private Integer prodPrice;
	@ManyToOne
	@JoinColumn(name="prod_type_id")
	private ProductType prodType;
	@ManyToOne
	@JoinColumn(name="prod_line_id")
	private ProductLine prodLine;
	@Lob
    @Column(name = "prod_image")
    private Blob prodImage;

	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdNum() {
		return prodNum;
	}
	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}
	public Integer getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	public ProductType getProdType() {
		return prodType;
	}
	public void setProdType(ProductType prodType) {
		this.prodType = prodType;
	}
	public ProductLine getProdLine() {
		return prodLine;
	}
	public void setProdLine(ProductLine prodLine) {
		this.prodLine = prodLine;
	}
	public Blob getProdImage() {
		return prodImage;
	}
	public void setProdImage(Blob prodImage) {
		this.prodImage = prodImage;
	}

}
