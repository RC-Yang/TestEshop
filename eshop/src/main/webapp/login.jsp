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
</head>
<body>
	<!-- 顯示錯誤訊息 -->
     <c:if test="${not empty msg}">
         <c:out value="${msg}" />
     </c:if>

	<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
	  <div class="p-4 border rounded" style="width: 100%; max-width: 400px;">
	    <form id="loginForm" action="login/login" method="post">
	      <div class="mb-3">
	        <label for="loginId" class="form-label">使用者名稱：</label>
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
	      <button type="submit" class="btn btn-primary">提交</button>&nbsp;
	      <a class="btn btn-success" href="register/register" style="text-decoration: none;">註冊</a>
	    </form>
	  </div>
	</div>

    <script type="text/javascript">
        function validateAndSubmit() {
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

            $("#loginForm").submit();
        }
    </script>
</body>
</html>
