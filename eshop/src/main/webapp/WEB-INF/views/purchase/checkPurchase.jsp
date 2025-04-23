<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

	<title>購買商品</title>
	<script>
		function toggleCreditCardForm() {
			const creditRadio = document.getElementById('creditCardOption');
			const form = document.getElementById('creditCardForm');
			
			if (creditRadio.checked) {
			  form.style.display = 'block';
			} else {
			  form.style.display = 'none';
			}
		}
	</script>
</head>
<body>
<%@ include file="../exampleNav.jspf" %>
    <h2 class="my-2">購買商品</h2>
    <form id="paymentForm" action="<%= request.getContextPath() %>/purchase/purchase" method="post">

	
		<div class="container mt-5" style="max-width: 600px;">
			  <h4 class="mb-4">選擇付款方式</h4>
			    <!-- 付款方式選擇 -->
			    <div class="mb-3">
			      <div class="form-check">
			        <input class="form-check-input" type="radio" name="paymentMethod" id="creditCardOption" value="credit" onclick="toggleCreditCardForm()">
			        <label class="form-check-label" for="creditCardOption">
			          信用卡付款
			        </label>
			      </div>
			      <div class="form-check">
			        <input class="form-check-input" type="radio" name="paymentMethod" id="atmOption" value="atm" onclick="toggleCreditCardForm()">
			        <label class="form-check-label" for="atmOption">
			          ATM 轉帳
			        </label>
			      </div>
			    </div>
			
			    <!-- 送出 -->
			    <div class="d-grid gap-2 col-6 mx-auto mt-2">
			      <button type="submit" class="btn btn-primary">確認付款</button>
			      <a href="<%= request.getContextPath() %>/cart/queryCart?loginId=${session_user.loginId}" class="btn btn-secondary" >取消</a>
			    </div>
		  </div>
	  </form>
</body>
</html>