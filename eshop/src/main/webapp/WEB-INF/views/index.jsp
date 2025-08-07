<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>EShop首頁</title>
</head>
<body class="d-flex flex-column min-vh-100">
<nav class="navbar navbar-expand bg-info">
  <div class="container-fluid 
  		d-flex justify-content-between align-items-center px-3">
    <a class="navbar-brand 
    	d-inline-flex justify-content-center align-items-center" href="#">EShop</a>
	<div class="d-flex justify-content-center align-items-center">   
	    <ul class="navbar-nav 
	    	d-flex justify-content-center align-items-center">
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	          購買商品
	        </a>
	        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <li><a class="dropdown-item" href="<%= request.getContextPath() %>/product/productList">商品列表</a></li>
	          <c:if test="${session_user.userType eq 2}">
	          	<li><a class="dropdown-item" href="#">商品管理</a></li>
	          </c:if>
	        </ul>
	      </li>

	      <c:if test="${session_user.userType eq 1}">
		      <li class="nav-item">
		        <a class="nav-link" href="<%= request.getContextPath() %>/cart/queryCart?loginId=${session_user.loginId}">購物車</a>
		      </li>
	      </c:if>

	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	          訂單資訊
	        </a>
	        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	        	<c:if test="${session_user.userType eq 1}">
	          		<li><a class="dropdown-item" href="<%= request.getContextPath() %>/order/queryOrderList?page=1&size=10">訂單詳細資訊</a></li>
	          	</c:if>
	          <c:if test="${session_user.userType eq 2}">
	          	<li><a class="dropdown-item" href="<%= request.getContextPath() %>/order/queryAllOrder">訂單詳細資訊</a></li>
	          	<li><a class="dropdown-item" href="#">訂單狀態更新</a></li>
	          </c:if>
	        </ul>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	          使用者管理
	        </a>
	        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <li><a class="dropdown-item" href="#">密碼重置</a></li>
	          <li><a class="dropdown-item" href="#">修改個人資料</a></li>
	          <li><a class="dropdown-item" href="#">管理收貨地址</a></li>
	        </ul>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="<%= request.getContextPath() %>/home/logout">登出</a>
	      </li>
	      </ul>
	      你好！${session_user.loginId}！
		  <form class="d-flex justify-content-center align-items-center">
		      <input class="form-control mx-1" type="search" placeholder="Search" aria-label="Search">
		      <button class="btn btn-outline-success mx-1" type="submit">Search</button>
		      <!-- Flex容器中的子元素margin不會重疊 -->
		  </form>		  
	  </div>
  </div>
</nav>
<main class="flex-grow-1"><!-- 確保該flex容器，可以佔有剩餘畫面全部的部分 -->

</main>
<footer class="d-flex justify-content-center align-items-center bg-info text-dark py-3">eshop</footer>
</body>
</html>