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
		<table id="myTable" class="table table-striped table-bordered">
		  <thead class="table-primary">
		    <tr>
		      <th>商品名稱</th>
		      <th>價格</th>
		      <th>數量</th>
		    </tr>
		  </thead>
		  <tbody>
			  <s:iterator value="cartList" var="cart">
			    <tr>
			      <td><s:property value="#cart[0]" /></td>
			      <td><s:property value="#cart[1]" /></td>
			      <td><s:property value="#cart[2]" /></td>
			    </tr>
			  </s:iterator>
		  </tbody>
		</table>
	</div>
</body>
<script>
  $(document).ready(function() {
    $('#myTable').DataTable({
      // optional: 調整語言（中文化）
      language: {
        url: "//cdn.datatables.net/plug-ins/1.13.6/i18n/zh-HANT.json"
      }
    });
  });
</script>

</html>