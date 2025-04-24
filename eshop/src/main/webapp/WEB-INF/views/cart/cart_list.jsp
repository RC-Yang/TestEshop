<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	
	<!-- DataTables CSS -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
	
	<!-- DataTables Bootstrap5 整合樣式 -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
	
	<!-- DataTables JS -->
	<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
	
	<!-- DataTables Bootstrap5 JS -->
	<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>
	<title>購物車清單</title>
</head>
<body>
	<%@ include file="../exampleNav.jspf" %>
    <h2 class="my-2">購物車清單</h2>

	<div class="w-75 mx-auto">
	 	<form action="<%= request.getContextPath() %>/purchase/goToPurchase" method="post" id="purchaseForm">
		<table id="myTable" class="table table-striped table-bordered">
		  <thead class="table-primary">
		    <tr>
		      <th>商品名稱</th>
		      <th>價格</th>
		      <th>數量</th>
		      <th>是否購買</th>
		    </tr>
		  </thead>
		  <tbody>
		  	 
		  	  		<% int index = 0; %>
				  <s:iterator value="cartList" var="cart" status="i">
				    <tr>
				      
					      <td><s:property value="#cart[0]" /></td>
					      <td><s:property value="#cart[1]" /></td>
					      <td>
					      	<input type="hidden" name='<%="cartItem[" + index + "].prodId" %>'
					      	 value="<s:property value="#cart[3]" />" id="prodId_<%=index%>"/>
					      	<select class="form-select" name='<%="cartItem[" + index + "].quantity" %>'
					      			id="quantity_<%=index%>">
					      		<s:iterator begin="1" end="%{#cart[2]}" var="j">
								    <option value="<s:property value='#j' />"
								      <s:if test="#j == 1">selected</s:if>>
								      <s:property value="#j" />
								    </option>
							  </s:iterator>
					      	</select>
					      </td>
					      <td>
				      		<div class="form-check p-0 m-0 d-flex justify-content-center align-items-center">
							  <input class="form-check-input" type="checkbox" checked id="checkbox_<%=index%>"
							  	name='selectedIndexes' value="<%= index %>">
							</div>
					      </td>
				    </tr>
				    <% index++; %>
				  </s:iterator>
			  
		  </tbody>
		</table>
		</form>
	</div>
	<div class="text-center">
		<button type="button" class="btn btn-success" onclick="purchase();">購買</button>
		<a href="<%= request.getContextPath() %>/product/productList" class="btn btn-secondary">
		  返回商品清單
		</a>
	</div>
</body>
<script>
  $(document).ready(function() {
    $('#myTable').DataTable({
      // optional: 調整語言（中文化）
      language: {
        url: "https://cdn.datatables.net/plug-ins/1.13.6/i18n/zh-HANT.json"
      }
    });
  });
  
  function purchase(){
	  document.getElementById("purchaseForm").submit();
  }
</script>

</html>