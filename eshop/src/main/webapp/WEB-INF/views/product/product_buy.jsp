<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳情</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		$(document).ready(function(){
			document.getElementById("addToCart").addEventListener("click",function(){
				addToCart();
			});
			document.getElementById("collect").addEventListener("click",function(){
				collect();
			});
			
			function addToCart() {
				  fetch('<%= request.getContextPath() %>/cart/addToCart', {
				    method: 'POST',
				    headers: {
				      'Content-Type': 'application/x-www-form-urlencoded',
				      'X-Requested-With': 'XMLHttpRequest'
				    },
				    body: "prodId=<s:property value='productDetail[4]' />&userId=${session_user.loginId}&quantity="+document.getElementById('quantity').value
				  })
				  .then((res)=>{return res.json();})
				  
				  .then((data) => {
						if(data.message=='商品成功加入購物車！'){
							  var myModal = new bootstrap.Modal(document.querySelector('#modal1'));
							  myModal.show();
						}
				  })
				  .catch(() => {
				    alert('加入購物車失敗，請稍後再試。');
				  });
				}
				
				function collect(){
			
				}
		});
	</script>
</head>
<body>
	<div id="modal1" class="modal fade" tabindex="-1">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">加入購物車</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>加入購物車成功！</p>
	      </div>
	      <div class="modal-footer">
	        <a class="btn btn-secondary" data-bs-dismiss="modal">取消</a>
	        <a href="<%= request.getContextPath() %>/cart/queryCart?loginId=${session_user.loginId}" class="btn btn-primary" >確定</a>
	      </div>
	    </div>
	  </div>
	</div>

	<%@ include file="../exampleNav.jspf" %>
    <h2 class="my-2">訂購商品</h2>

	<div class="d-flex justify-content-center align-items-center">
	  <!-- 商品圖片 -->
	  <img src="<%= request.getContextPath() %>/product/productImage?prodId=<s:property value='productDetail[4]' />" 
	  		class="img-fluid rounded" alt="商品圖片">
	  <!-- 這個src，是一被瀏覽器解析到，就立刻執行 -->
	  <div style="padding:0 20px;"></div>
	  <!-- 商品資訊 -->
	  <div>
	    <h2 class="fw-bold"><s:property value="productDetail[0]" /></h2>
	    <h3 class="mb-4"><s:property value="productDetail[1]" /></h3>
	
	    <h3 class="mb-4"><s:property value="productDetail[2]" /></h3>
	    <h3 class="mb-4">$<span class="text-danger"><s:property value="productDetail[3]" /></span>元</h3>
	
		
		<c:set var="amount1" value="<s:property value='productDetail[5]' />"/>
		<s:set var="amount1" scope="request" value="<s:property value='productDetail[5]' />"/>
		<s:set var="amount" scope="request" value="%{productDetail[5]}"/>
		已選取
		<select class="form-select d-inline-block w-auto" id="quantity">	
			<c:choose>
				<c:when test="${amount==0}">
					<option disabled>0</option>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="1" end="${amount}">
						<c:if test="${i==1}">
							<option value="${i}" selected>${i}</option>
						</c:if>
						<c:if test="${i!=1}">
							<option value="${i}">${i}</option>
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</select>
		件商品
		
		<p class="mb-4">目前還剩<s:property value="productDetail[5]" />個商品</p>
	    <div class="d-grid gap-2 d-md-block">
	    <c:choose>
			<c:when test="${amount==0}">
	    		<a id="addToCart" class="btn btn-primary me-2 disabled" aria-disabled="true">加入購物車</a>
	    	</c:when>
			<c:otherwise>
				<a id="addToCart" class="btn btn-primary me-2">加入購物車</a>
			</c:otherwise>
		</c:choose>
	    	<a id="collect" class="btn btn-secondary me-2">收藏</a>
	    </div>
	  </div>
	</div>
    
</body>
</html>