<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>付款成功</title>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<!-- Bootstrap Modal 結構 -->
	<div class="modal fade" id="successModal" tabindex="-1" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title text-success">付款成功</h5>
	      </div>
	      <div class="modal-body">
	        感謝您的訂購，我們已收到您的付款。<br>
	        您的訂單將盡快處理。
	      </div>
	      <div class="modal-footer">
	        <a href="/eshop/home/index" class="btn btn-primary">返回首頁</a>
	        <a href="/eshop/order/queryOrderList" class="btn btn-outline-secondary">查看訂單</a>
	      </div>
	    </div>
	  </div>
	</div>
	<script>
	  window.onload = function () {
	    var modal = new bootstrap.Modal(document.getElementById('successModal'));
	    modal.show();
	  };
	</script>
</body>
</html>