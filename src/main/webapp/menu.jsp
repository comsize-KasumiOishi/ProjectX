<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>メニュー画面</title>
</head>
<body>
	<% String userName = (String) session.getAttribute("userName");%>
	<%@ include file="header.jsp"%>
	<h2>メニュー画面</h2>
	<hr>
	ようこそ！<%= userName%>さん<br>
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