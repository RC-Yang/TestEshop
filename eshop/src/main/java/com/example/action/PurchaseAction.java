package com.example.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.constant.ConstantName;
import com.example.dto.CartForm;
import com.example.dto.CartItem;
import com.example.pojo.entity.User;
import com.example.service.OrderService;
import com.example.service.PurchaseService;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

public class PurchaseAction extends BaseAction{
	
	@Autowired
	private OrderService orderService;
	
	private Object[] product;
	
	//private List<CartItem> cartItem = new ArrayList<>();
	private CartForm model = new CartForm();
	
	private AllInOne all = new AllInOne("");

	public PurchaseAction() throws Exception {
		super();
	}
	
	public CartForm getModel() {
		return model;
	}

	public void purchase() throws IOException {
		
		User user = (User)getSession().getAttribute(ConstantName.SESSION_USER);

		String orderNum = orderService.createOrder(user.getLoginId(), (List<CartItem>)getSession().getAttribute("chosenCartItems"));

		getSession().setAttribute("orderNum", orderNum);
		
		AioCheckOutALL obj = new AioCheckOutALL();
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        obj.setMerchantTradeNo("EC" + timeStamp); // 訂單編號
        obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        obj.setTotalAmount("100");
        obj.setTradeDesc("綠界 SDK 測試單");
        obj.setItemName("測試商品x1");
        obj.setReturnURL("http://localhost:8080/eshop/purchase/cashFinish");
        obj.setClientBackURL("http://localhost:8080/eshop/purchase/goToCashSuccess");
        obj.setNeedExtraPaidInfo("N");
        //obj.setChoosePayment("Credit");

        // 產生 HTML 表單（含自動送出）
        String form = all.aioCheckOut(obj, null);

        // 寫到 response，送出給綠界
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(form);
        out.flush();

        return ; // 直接寫出 HTML，不走 view 路徑
	}
	
	public String goToPurchase() {
		
		List<CartItem> cartitemList = model.getCartItem();
		
		for (int i = 0; i < cartitemList.size(); i++) {
			CartItem it = model.getCartItem().get(i);
		    System.out.printf("[%d] prodId=%s, qty=%s, selected=%s%n",
		        i, it.getProdId(), it.getQuantity(), it.getSelected());
		}
		
		List<CartItem> chosen = model.getCartItem().stream()
	            .filter(it -> Boolean.TRUE.equals(it.getSelected()))
	            .collect(Collectors.toList());
		
		getSession().setAttribute("chosenCartItems", chosen);
		
		return SUCCESS;
	}
	
	public String goToCashSuccess() {
		return SUCCESS;
	}
	
	public String cashFinish() {
		String orderNum = (String)getSession().getAttribute("orderNum");
		orderService.updateOrderState(orderNum, "已付款");
		return SUCCESS;
	}

	public Object[] getProduct() {
		return product;
	}

	public void setProduct(Object[] product) {
		this.product = product;
	}
}
