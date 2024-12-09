<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク削除失敗画面</title>
</head>
<body>
	<%
		int taskId = (int) session.getAttribute("taskId");
		String taskName = (String) session.getAttribute("taskName");
		String categoryName = (String) session.getAttribute("categoryName");
		Date limitDate = (Date) session.getAttribute("limitDate");
		String userName = (String) session.getAttribute("userName");
		String statusName = (String) session.getAttribute("statusName");
		String memo = (String) session.getAttribute("memo");
	%>

	<h1>タスク削除失敗画面</h1>
	<hr>
	
	<h3>以下のデータを削除できませんでした。</h3>
	<br>
	
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
		<td><%= memo%></td>
	</tr>
	</table>
	
	<br>

	<form action="TaskListServlet" method="POST">
	<input type="submit" value="一覧に戻る">
	</form>

</body>
</html>