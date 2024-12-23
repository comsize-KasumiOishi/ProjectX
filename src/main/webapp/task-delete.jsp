<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date,model.entity.TaskCategoryUserStatusBean,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>タスク削除画面</title>
</head>
<body>
	<%
		//詳細画面に表示したBean型を取得
		TaskCategoryUserStatusBean tcusbean = (TaskCategoryUserStatusBean) session.getAttribute("detail");
		
		int taskId = tcusbean.getTaskId();
		String taskName = tcusbean.getTaskName();
		String categoryName = tcusbean.getCategoryName();
		Date limitDate = tcusbean.getLimitDate();
		String userName = tcusbean.getUserName();
		String statusName = tcusbean.getStatusName();
		String memo = tcusbean.getMemo();
	%>

	<h2>タスク削除画面</h2>
	<hr>
	
	<table border>
	<tr>
		<td>タスクID</td>
		<td><%= taskId%></td>
	</tr>
	<tr>
		<td>タスク名</td>
		<td><%= taskName%></td>
	</tr>
	<tr>
		<td>カテゴリー名</td>
		<td><%= categoryName%></td>
	</tr>
	<tr>
		<td>期限</td>
		<td><%= limitDate%></td>
	</tr>
	<tr>
		<td>ユーザー名</td>
		<td><%= userName%></td>
	</tr>
	<tr>
		<td>ステータス名</td>
		<td><%= statusName%></td>
	</tr>
	<tr>
		<td>メモ</td>
		<% 
		if(memo == null || memo.isEmpty()){
		%>
		<td></td>
		<%}else{ %>
		<td><%= memo%></td>
		<%} %>
	</tr>
	</table>
	
	<br>
	<h3>以上のデータを削除しますか？</h3>
	<br>

	<form action="TaskListServlet" method="POST">
	<input type="submit" id="button" value="一覧に戻る">
	</form>
	
	<form action="TaskDeleteServlet" method="POST">
	<input type="submit" id="button" value="削除">
	</form>
	
</body>
</html>