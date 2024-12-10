<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.TaskCategoryUserStatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
		タスク名：<input type="text" name="taskName" required><br> 
		カテゴリ分類：<select name="category"><%
						for (TaskCategoryUserStatusBean tcusbean : categoryList) {
						%>
						<option value="<%=tcusbean.getCategoryName()%>,<%=tcusbean.getCategoryId()%>"><%=tcusbean.getCategoryName()%></option>
						<%
						}
						%></select><br>
		期限：<input type="date" name="limitDate" min="<%=today%>"><br> 
		担当者：<select name="user"><%
						for (TaskCategoryUserStatusBean tcusbean : userList) {
						%>
						<option value="<%=tcusbean.getUserName()%>,<%=tcusbean.getUserId()%>"><%=tcusbean.getUserName()%></option>
						<%
						}
						%></select><br>
		ステータス情報：<select name="status"><%
						for (TaskCategoryUserStatusBean tcusbean : statusList) {
						%>
						<option value="<%=tcusbean.getStatusName()%>,<%=tcusbean.getStatusCode()%>"><%=tcusbean.getStatusName()%></option>
						<%
						}
						%></select><br>
		メモ：<input type="textarea" name="memo"><br> 
		<input type="submit" value="登録"> 
		<input type="reset" value="取消">
	</form>
	<form action = "menu.jsp" method = "GET">
	<input type="submit" value="メニュー画面へ"> 
	</form>
</body>
</html>