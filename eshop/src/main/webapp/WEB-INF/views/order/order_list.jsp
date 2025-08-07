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

	<link href="https://unpkg.com/bootstrap-vue@2.21.2/dist/bootstrap-vue.css" rel="stylesheet">	
	<!-- Vue 2.x -->
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>	
	<!-- BootstrapVue JavaScript -->
	<script src="https://unpkg.com/bootstrap-vue@2.21.2/dist/bootstrap-vue.js"></script>
</head>
<body>
	<%@ include file="../exampleNav.jspf" %>
    <h2 class="my-2">訂單詳情</h2>
    <div id="app">
	    <div class="w-75 mx-auto">
			<div class="accordion" id="orderAccordion">
			    <s:iterator value="orderPageList" var="order" status="st">
			        <div class="accordion-item" v-show="showItem(<s:property value='%{#st.index}'/>)">
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
		</div>

		<!-- 下兩行，即可將當前頁碼和currentPage給相互綁定 -->
		<!-- 搭配currentPage已被設置成被Vue監聽，當這些變數發生改變(例如當前頁碼被改變之時)，Vue物件作用的html區塊，跟currentPage關聯的部分，便會重新渲染 -->
        <b-pagination
         v-model="currentPage"
         :total-rows="totalItems"
         :per-page="itemsPerPage"
         :page-range="10"
         :next-text="'Next'"
         :prev-text="'Previous'"
         class="d-flex justify-content-center my-2"
     ></b-pagination><!-- bootstrap-vue tag，完全兼容bootstrap -->
		
		<div class="text-center">
		   <a href="<%= request.getContextPath() %>/home/index" class="btn btn-secondary">
			  返回首頁
			</a> 
		</div>
	</div>

	<script>
        //為實作分頁功能而new Vue物件
        new Vue({
			el: '#app',//要將該vue物件跟該id範圍進行綁定
			data(){
				return{
					currentPage:1,//讓currentPage被Vue監聽，這樣一旦currentPage值改變，Vue監聽到之時，所有用到currentPage的html區塊都會重新渲染，即可做出分頁效果
					totalItems:document.querySelectorAll(".accordion-item").length,
					itemsPerPage:10
				};
			},
			methods:{
				showItem(index){
					if(index>=(this.currentPage-1)*this.itemsPerPage&&
							index<this.currentPage*this.itemsPerPage){
						return true;
					}
					return false;
				}
			}
        });
    </script>
</body>
</html>