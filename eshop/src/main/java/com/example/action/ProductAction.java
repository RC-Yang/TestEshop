package com.example.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.ProductService;

public class ProductAction extends BaseAction {

	@Autowired
	private ProductService productService;
	
	private List<Object[]> productList;

	 public String goToProductPage() throws Exception {
		//HttpSession session= getSession();
		//session.setAttribute("productList", productService.queryAllProduct(Integer.parseInt(customerId)));
		productList = productService.queryAllProduct();
        return SUCCESS;
    }	 

	public List<Object[]> getProductList() {
	    return productList;
	}
}
