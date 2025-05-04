package com.example.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.ProductService;

public class ProductAction extends BaseAction {

	@Autowired
	private ProductService productService;
	
	private List<Object[]> productList;
	private Object[] productDetail;
	private byte[] productImage;

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
	
	public void queryProdImageByProdId() throws IOException {
		String prodId = getRequest().getParameter("prodId");
		productImage = productService.findProdImageByProdId(Integer.parseInt(prodId));
		
		if(productImage==null) {
			String defaultImagePath = ServletActionContext
			        .getServletContext()
			        .getRealPath("/photo/photoSample.jpg");
			
			File file = new File(defaultImagePath);
			productImage = Files.readAllBytes(file.toPath());
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/jpeg");
		response.setContentLength(productImage.length);
		response.getOutputStream().write(productImage);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
		return;
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

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}
}
