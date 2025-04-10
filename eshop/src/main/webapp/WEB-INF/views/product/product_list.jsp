<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品列表</title>
<link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
 <link rel="stylesheet"
          href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css"/>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
   
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="../exampleNav.jspf" %>
    <h2 class="my-2">商品列表</h2>

	<div class="w-75 mx-auto">
	    <table id="productTable" class="table table-striped table-bordered">
	        <thead class="table-primary">
	        <tr>
	            <th>編號</th>
	            <th>商品名稱</th>
	            <th>價格</th>
	        </tr>
	        </thead>
	        <tbody>
	        <s:iterator value="productList" var="product">
	            <tr>
	                <td><s:property value="#product[0]"/></td>
	                <td><s:property value="#product[1]"/></td>
	                <td><s:property value="#product[2]"/></td>
	            </tr>
	        </s:iterator>
	        </tbody>
	    </table>
	</div>

    <script>
        $(document).ready(function () {
            $('#productTable').DataTable({
                language: {
                    url: "//cdn.datatables.net/plug-ins/1.13.6/i18n/zh-HANT.json"
                }
            });
        });
    </script>
</body>
</html>