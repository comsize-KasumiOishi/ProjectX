<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link rel="stylesheet" href="style.css" >
</head>
<body>
	<% String userName = (String) session.getAttribute("userName");%>
	<%@ include file="header.jsp"%>
	<h1>メニュー画面</h1>
	<hr>
	<h4>ようこそ！<%= userName%>さん</h4>
	<br>
	
	<form action="TaskAddServlet" method="GET">
	
	<input type="submit" id="button" value="タスク登録">
	
	</form>
	
	<br>
	
	<form action="TaskListServlet" method="GET">
	
	<input type="submit" id="button" value="タスク一覧表示">
	
	</form>
	
	<br>
	
	<form action="logout-success.jsp" method="GET">
	
	<input type="submit" id="button" value="ログアウト">
		
	</form>
</body>
</html>