<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>ログアウト画面</title>
</head>
<body>
	<h2>ログアウト画面</h2>
	<hr>
	<% String userName = (String) session.getAttribute("userName");%>
	
	お疲れさまでした！<%= userName%>さん
	
	<h3>ログアウトしました。</h3>
	
	<% session.invalidate();%>
	
	<form action="login.jsp" method="GET">
	
	<input type="submit" id="button" value="ログイン画面へ">
	<br>
	<br>
	</form>	
</body>
</html>