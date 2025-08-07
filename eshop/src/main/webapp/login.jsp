<%@ page import="com.example.pojo.entity.User" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
    // 獲取當前會話中的用戶物件
    User user = (User) session.getAttribute("user");

    // 如果用戶已登入，則重新導向到首頁
    if (user != null && !"".equals(user.getLoginId())) {
        response.sendRedirect("home/index");
    }
%>
<%@ page import="com.example.*" %>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用戶登入</title>
    <script src="public/jquery-3.4.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>用戶登入</title>
    
    
    <script type="text/javascript">
        function validateAndSubmit(type) {
            let loginId = $("input[name='loginId']").val().trim();
            let password = $("input[name='password']").val().trim();

            if (!loginId) {
                alert("使用者名稱不能為空");
                return;
            }

            if (!password) {
                alert("密碼不能為空");
                return;
            }

            if(type==1){
            	$("#form").attr('action', '<%=request.getContextPath()%>/login/login');
            }
            else if(type==2){
            	$("#form").attr('action', '<%=request.getContextPath()%>/register/save');
            }

            $("#form").submit();
        }
        
        onload=function(){//$(document).ready(function(){})，不等於onload=function(){}
			document.querySelector("#registerButton").addEventListener("click",function(){
				validateAndSubmit(2);
			});
        }
    </script>
</head>
<body>
	<!-- 顯示錯誤訊息 -->
     <c:if test="${not empty msg}">
     <!-- 在 XML 標準中，屬性值必須用雙引號或單引號包起來，並非表示該EL為字串
     		所以這裡的雙引號，就跟${not empty msg}到底是不是字串格式，完全無關 -->
     <!-- 故JSTL所有的屬性值，都必須用雙引號框住了 -->

         <c:out value="${msg}" />
         <!-- 可避免XSS：其會將HTML Code，例如<、>、&，轉成純文字格式：
         &lt;&gt;&amp;，這些符號，會被瀏覽器轉成對應的純文字格式：<、>、& -->
    	 <!-- 如何處理${msg}為null的問題->輸出空字串 -->
     </c:if>
     <c:if test="${not empty registerResult}"><!-- 如果c:if是false，那麼被包圍起來的程式，完全不會被輸出到瀏覽器 -->
     	<script>
     		alert('<c:out value="${registerResult}" />');
     	</script>
     	<c:remove var="registerResult" scope="session"/>
     	<!-- registerResult放在session內，在註冊、登入完後，就移除掉 -->
     </c:if>

	<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
	  <div class="p-4 border rounded" style="width: 100%; max-width: 400px;">
	    <form id="form" action="" method="post">
	      <div class="mb-3">
	        <label for="loginId" class="form-label">帳號：</label>
	        <input type="text" class="form-control" id="loginId" name="loginId" required>
	        <div id="emailHelp" class="form-text"><c:out value="${loginId}"/></div>
	      </div>
	      <div class="mb-3">
	        <label for="password" class="form-label">密碼：</label>
	        <input type="password" class="form-control" id="password" name="password" required>
	      </div>
	      <div class="form-check form-check-inline">
		  	<input class="form-check-input" type="radio" name="userType" id="user" value="1">
		  	<label class="form-check-label" for="user">一般用戶</label>
		</div>
		<div class="form-check form-check-inline">
		  	<input class="form-check-input" type="radio" name="userType" id="admin" value="2">
		  	<label class="form-check-label" for="admin">管理者</label>
		</div>
	      <button type="button" class="btn btn-primary" onclick="validateAndSubmit(1)">登入</button>&nbsp;
	      <button type="button" class="btn btn-success" id="registerButton">註冊</button>
	    </form>
	  </div>
	</div>
</body>
</html>
