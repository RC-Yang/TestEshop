package com.example.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.ProductService;

public class ProductAction extends BaseAction {

	@Autowired
	private ProductService productService;
	
	private List<Object[]> productList;
	private Object[] productDetail;
	private InputStream productImageStream;
	private Long contentLength;

	 public String goToProductPage() throws Exception {
		//HttpSession session= getSession();
		//session.setAttribute("productList", productService.queryAllProduct(Integer.parseInt(customerId)));
		productList = productService.queryAllProduct();
        return SUCCESS;
    }
	 
	public String queryProductDetail() {
		String prodId = getRequest().getParameter("prodId");
		productDetail = productService.queryProductById(Integer.parseInt(prodId));
		return SUCCESS;
	}
	
	public String queryProdImageByProdId() throws IOException, NumberFormatException, SQLException {
		String prodId = getRequest().getParameter("prodId");

		productImageStream = productService.findProdImageByProdId(Integer.parseInt(prodId)).getBinaryStream();
		
		if(productImageStream==null) {
			String defaultImagePath = ServletActionContext
			        .getServletContext()
			        .getRealPath("/photo/photoSample.jpg");
			
			File file = new File(defaultImagePath);
			contentLength = (Long) file.length();
			productImageStream = new FileInputStream(file);
		}
		
		contentLength = productService.findProdImageByProdId(Integer.parseInt(prodId)).length();
		
		return SUCCESS;
	}

	public List<Object[]> getProductList() {
	    return productList;
	}

	public Object[] getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(Object[] productDetail) {
		this.productDetail = productDetail;
	}

	public void setProductList(List<Object[]> productList) {
		this.productList = productList;
	}

	public InputStream getProductImageStream() {
		return productImageStream;
	}

	public void setProductImageStream(InputStream productImageStream) {
		this.productImageStream = productImageStream;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}
}
