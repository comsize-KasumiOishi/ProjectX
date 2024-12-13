<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<% String userId = (String) session.getAttribute("userId");%>
	<% String password = (String) session.getAttribute("password");%>
	<% String userName = (String) session.getAttribute("userName");%>
	
	<h3>メニュー画面</h3>
	<hr>
	ようこそ！<%= userName%>さん<br>
	<br>
	
	<form action="TaskAddServlet" method="GET">
	
	<input type="submit" value="タスク登録">
	
	</form>
	
	<br>
	
	<form action="TaskListServlet" method="GET">
	
	<input type="submit" value="タスク一覧表示">
	
	</form>
	
	<br>
	
	<form action="logout-success.jsp" method="GET">
	
	<input type="submit" value="ログアウト">
		
	</form>
</body>
</html>