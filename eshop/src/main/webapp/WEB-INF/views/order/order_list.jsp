<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>訂單詳情</title>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="../exampleNav.jspf" %>
    <h2 class="my-2">訂單詳情</h2>
    
    <div class="w-75 mx-auto">
	<div class="accordion" id="orderAccordion">
	    <s:iterator value="orderList" var="order" status="st">
	        <div class="accordion-item">
	            <h2 class="accordion-header" id="heading<s:property value='%{#st.index}'/>">
	                <button class="accordion-button collapsed" type="button"
	                        data-bs-toggle="collapse"
	                        data-bs-target="#collapse<s:property value='%{#st.index}'/>"
	                        aria-expanded="false"
	                        aria-controls="collapse<s:property value='%{#st.index}'/>">
	                    訂單編號：<s:property value="#order.ordNum"/>｜
	                    金額：$<s:property value="#order.amount"/>｜
	                    狀態：<s:property value="#order.state"/>
	                </button>
	            </h2>
	            <div id="collapse<s:property value='%{#st.index}'/>"
	                 class="accordion-collapse collapse"
	                 aria-labelledby="heading<s:property value='%{#st.index}'/>"
	                 data-bs-parent="#orderAccordion">
	                <div class="accordion-body">
	                    <p><strong>商品：</strong> <s:property value="#order.product.prodName"/></p>
	                    <p><strong>數量：</strong> <s:property value="#order.ordQty"/></p>
	                    <p><strong>顧客姓名：</strong> <s:property value="#order.customer.custName"/></p> 
	                </div>
	            </div>
	        </div>
	    </s:iterator>
	</div>
	<div class="text-center mt-2">
	   <a href="<%= request.getContextPath() %>/home/index" class="btn btn-secondary">
		  返回首頁
		</a> 
	</div>
</body>
</html>