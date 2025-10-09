package com.example.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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
		//HttpSession session= getSession(false);也可以，因為兩者都會取出既有的session 
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

		Blob productImageBlob = null;
		productImageBlob = productService.findProdImageByProdId(Integer.parseInt(prodId));
		
		if(productImageBlob!=null) {
			productImageStream=productImageBlob.getBinaryStream();
			contentLength = productService.findProdImageByProdId(Integer.parseInt(prodId)).length();
		}else{
			String defaultImagePath = ServletActionContext
			        .getServletContext()//ServletContext ，其實是用來管理整個專案的物件
			        .getRealPath("/photo/photoSample.jpg");
			//專案是在布署後，才能正式運作，故要取得該圖片，必須到該圖被布署的路徑去找，故不能直接將路徑寫成src/main/webapp
			//該圖被布署的路徑，就是以「專案名稱」為開頭，加上「/photo/photoSample.jpg」的絕對路徑
			//故路徑參數，寫成/photo/photoSample.jpg
			
			File file = new File(defaultImagePath);
			contentLength = (Long) file.length();
			productImageStream = new FileInputStream(file);
		}
		
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
