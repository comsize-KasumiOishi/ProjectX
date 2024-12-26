<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.TaskCategoryUserStatusBean"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap" rel="stylesheet">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>タスク登録画面</title>
</head>
<body>
	<%--TaskAddServletのdoGetメソッドでセッションスコープに設定したリストと
	doPostメソッドで設定した今日の日付を取得する--%>
	<%
	List<TaskCategoryUserStatusBean> categoryList = (List<TaskCategoryUserStatusBean>) session.getAttribute("categoryList");
	List<TaskCategoryUserStatusBean> userList = (List<TaskCategoryUserStatusBean>) session.getAttribute("userList");
	List<TaskCategoryUserStatusBean> statusList = (List<TaskCategoryUserStatusBean>) session.getAttribute("statusList");
	String today = (String) session.getAttribute("strToday");%>
	<h1>タスク登録画面</h1>
	<form action="TaskAddServlet" method="POST">
		タスク名：<div class="cp_iptxt"><input type="text" name="taskName" maxlength="50" required></div>
		カテゴリ分類：<div class="wrapper"><select name="category"></div><%
						for (TaskCategoryUserStatusBean tcusbean : categoryList) {
						%>
						<option value="<%=tcusbean.getCategoryName()%>,<%=tcusbean.getCategoryId()%>"><%=tcusbean.getCategoryName()%></option>
						<%
						}
						%></select><br>
		期限：<input type="date"  name="limitDate" min="<%=today%>"><br> 
		担当者：<div class="wrapper"><select name="user"></div><%
						for (TaskCategoryUserStatusBean tcusbean : userList) {
						%>
						<option value="<%=tcusbean.getUserName()%>,<%=tcusbean.getUserId()%>"><%=tcusbean.getUserName()%></option>
						<%
						}
						%></select><br>
		ステータス情報：<div class="wrapper"><select name="status"></div><%
						for (TaskCategoryUserStatusBean tcusbean : statusList) {
						%>
						<option value="<%=tcusbean.getStatusName()%>,<%=tcusbean.getStatusCode()%>"><%=tcusbean.getStatusName()%></option>
						<%
						}
						%></select><br>
		メモ：<div class="cp_iptxt"><input type="textarea" name="memo" maxlength="100"></div><br> 
		<input type="submit" id="button" value="登録"> 
		<input type="reset" id="button" value="取消">
	</form>
	<form action = "menu.jsp" method = "GET">
	<input type="submit" id="button" value="メニュー画面へ"> 
	</form>
</body>
</html>