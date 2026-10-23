package com.example.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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

public class PurchaseAction extends BaseAction {

	@Autowired
	private OrderService orderService;

	private Object[] product;

	// private List<CartItem> cartItem = new ArrayList<>();
	private CartForm model = new CartForm();

	private AllInOne all = new AllInOne("");

	public PurchaseAction() throws Exception {
		super();
	}

	public CartForm getModel() {
		return model;
	}

	public void purchase() throws IOException {

		User user = (User) getSession().getAttribute(ConstantName.SESSION_USER);

		String orderNum = orderService.createOrder(user.getLoginId(),
				(List<CartItem>) getSession().getAttribute("chosenCartItems"));

		getSession().setAttribute("orderNum", orderNum);

		AioCheckOutALL obj = new AioCheckOutALL();

		obj.setMerchantTradeNo(orderNum); // 訂單編號
		obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		obj.setTotalAmount("100");
		obj.setTradeDesc("綠界 SDK 測試單");
		obj.setItemName("測試商品x1");
		obj.setReturnURL("https://issue-belfast-shower-vocals.trycloudflare.com/eshop/purchase/cashFinish");
		obj.setClientBackURL("https://issue-belfast-shower-vocals.trycloudflare.com/eshop/purchase/goToCashSuccess");
		obj.setNeedExtraPaidInfo("N");
		// obj.setChoosePayment("Credit");

		// 產生 HTML 表單（含自動送出）
		String form = all.aioCheckOut(obj, null);

		// 寫到 response，送出給綠界
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(form);
		out.flush();

		return; // 直接寫出 HTML，不走 view 路徑
	}

	public String goToPurchase() {

		List<CartItem> cartitemList = model.getCartItem();

		for (int i = 0; i < cartitemList.size(); i++) {
			CartItem it = model.getCartItem().get(i);
			System.out.printf("[%d] prodId=%s, qty=%s, selected=%s%n", i, it.getProdId(), it.getQuantity(),
					it.getSelected());
		}

		List<CartItem> chosen = model.getCartItem().stream().filter(it -> Boolean.TRUE.equals(it.getSelected()))
				.collect(Collectors.toList());

		getSession().setAttribute("chosenCartItems", chosen);

		return SUCCESS;
	}

	public String goToCashSuccess() {
		return SUCCESS;
	}

	public String cashFinish() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("text/plain;charset=UTF-8");

		// 1. 取得綠界 POST 傳來的所有參數
		Hashtable<String, String> params = new Hashtable<>();

		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

			String[] values = entry.getValue();

			if (values == null || values.length != 1) {
				response.setStatus(400);
				return NONE;
			}

			params.put(entry.getKey(), values[0]);
		}

		// 2. 使用綠界 SDK 驗證 CheckMacValue
		boolean valid;

		try {
			valid = all.compareCheckMacValue(params);
		} catch (Exception e) {
			response.setStatus(400);
			return NONE;
		}

		if (!valid) {
			response.setStatus(400);
			return NONE;
		}
		
		// 3. 取得付款結果
	    String rtnCode = params.get("RtnCode");

	    String orderNum = params.get("MerchantTradeNo");

	    String merchantID = params.get("MerchantID");

	    String tradeAmt = params.get("TradeAmt");

	 // 4. 此處仍須檢查：
	    // merchantID 是否為你的商店代號
	    // orderNum 是否存在於資料庫
	    // tradeAmt 是否等於訂單原始金額
	    // 這些檢查未完成前，不可執行下方更新

	    // 5. 付款成功才更新資料庫
	    if ("1".equals(rtnCode)) {

	        // 僅在第 4 步全部驗證通過後執行，
	        // 並應防止重複付款通知造成重複處理
	        orderService.updateOrderState(
	            orderNum, "已付款"
	        );
	    }

	    // 6. 告訴綠界已成功接收並處理通知
	    response.getWriter().write("1|OK");

	    return NONE;
	}

	public Object[] getProduct() {
		return product;
	}

	public void setProduct(Object[] product) {
		this.product = product;
	}
}
