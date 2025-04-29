<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<title>訂單資訊</title>
</head>
<body>
<%@ include file="../exampleNav.jspf" %>
    <h2 class="my-2">訂單詳情</h2>
    
    <div class="w-75 mx-auto">
		<div class="accordion" id="orderAccordion">
		    <s:iterator value="orderGroupList" var="orderGroup" status="st">
		        <div class="accordion-item">
		            <h2 class="accordion-header" id="heading<s:property value='%{#st.index}'/>">
		                <button class="accordion-button collapsed" type="button"
		                        data-bs-toggle="collapse"
		                        data-bs-target="#collapse<s:property value='%{#st.index}'/>"
		                        aria-expanded="false"
		                        aria-controls="collapse<s:property value='%{#st.index}'/>">
		                    訂單編號：<s:property value="#orderGroup.ordNum"/>
		                </button>
		            </h2>
		            <div id="collapse<s:property value='%{#st.index}'/>"
		                 class="accordion-collapse collapse"
		                 aria-labelledby="heading<s:property value='%{#st.index}'/>"
		                 data-bs-parent="#orderAccordion">
		                <div class="accordion-body">
		                    <table class="table">
					          <thead>
					            <tr>
					              <th>商品名稱</th>
					              <th>數量</th>
					              <th>單價</th>
					              <th>小計</th>
					            </tr>
					          </thead>
					          <tbody>
					            <s:iterator value="#orderGroup.orders" var="order">
					              <tr>
					                <td><s:property value="#order.product.prodName"/></td>
					                <td><s:property value="#order.ordQty"/></td>
					                <td>$<s:property value="#order.product.prodPrice"/></td>
					                <td>$<s:property value="#order.product.prodPrice * #order.ordQty"/></td>
					              </tr>
					            </s:iterator>
					          </tbody>
        					</table>
        					<!-- 出貨按鈕 -->
					        <form class="mt-3 shipForm">
					          <input type="hidden" name="ordNum" value="<s:property value='#orderGroup.ordNum'/>">
					          <input type="hidden" name="state" value="已出貨">
					          <button type="submit" class="btn btn-success">訂單出貨</button>
					        </form>
		                </div>
		            </div>
		        </div>
		        
		    </s:iterator>
		</div>
	</div>
	<div class="text-center mt-2">
	   <a href="<%= request.getContextPath() %>/home/index" class="btn btn-secondary">
		  返回首頁
		</a> 
	</div>
	
	<div class="modal fade" id="shipSuccessModal" tabindex="-1" aria-labelledby="shipSuccessModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="shipSuccessModalLabel">操作成功</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="關閉"></button>
	      </div>
	      <div class="modal-body">
	        訂單已成功出貨！
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="modalCloseBtn">關閉</button>
	      </div>
	    </div>
	  </div>
	</div>

	<script>
		let lastClickedButton = null;
	
	  $(document).ready(function () {
	    $('.shipForm').on('submit', function (e) {

	      e.preventDefault(); // 阻止表單預設提交行為
	      lastClickedButton = $(this).find('button[type="submit"]');

	      $.ajax({
	        url: '<%=request.getContextPath()%>/order/updateOrderStateByOrdNum', // 後端URL
	        method: 'POST',
	        data: $(this).serialize(), // 傳送表單所有欄位
	        success: function (response) {
	          if(response.message=='訂單已成功出貨！'){
	        	  const modal = new bootstrap.Modal(document.getElementById('shipSuccessModal'));
		          modal.show();
	          } 
	        },
	        error: function () {
	          alert("出貨失敗，請稍後再試。");
	        }
	      });
	    });
	    
	    $('#modalCloseBtn').on('click', function () {
	    	    lastClickedButton
	    	      .text('已出貨')
	    	      .removeClass('btn-success')
	    	      .addClass('btn-secondary')
	    	      .prop('disabled', true);
	    	})
	  });
	</script>	
</body>
</html>