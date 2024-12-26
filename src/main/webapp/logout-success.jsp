<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>ログアウト画面</title>
</head>
<body>
	<h1>ログアウト画面</h1>
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