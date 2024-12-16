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
	<% String userName = (String) session.getAttribute("userName");%>
	
	お疲れさまでした！<%= userName%>さん
	
	<h3>ログアウトしました。</h3>
	
	<% session.invalidate();%>
	
	<form action="login.jsp" method="GET">
	
	<input type="submit" value="ログイン画面へ">
	<br>
	<br>
	</form>	
</body>
</html>