<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
</head>
<body>
	<h3>ログアウト画面</h3>
	<hr>
	<% String userId = (String) session.getAttribute("userId");%>
	<% String password = (String) session.getAttribute("password");%>
	<% String userName = (String) session.getAttribute("userName");%>
	
	お疲れさまでした！<%= userName%>さん
	
	<h3>ログアウトしました。</h3>
	
	<form action="login.jsp" method="POST">
	
	<input type="submit" value="ログイン画面へ">
	<br>
	<br>
	</form>
	
	<% session.invalidate();%>
</body>
</html>