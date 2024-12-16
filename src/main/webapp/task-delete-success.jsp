<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date,model.entity.TaskCategoryUserStatusBean,java.util.List"%>
<!DOCTYPE html>
<!-- タスク削除完了画面 -->
<!-- @author 坂上 -->
<html>
<head>
<meta charset="UTF-8">
<title>タスク削除完了画面</title>
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
		if(tcusbean.getMemo() == null){
			String memo = "";
		}else{
		String memo = tcusbean.getMemo();
		}
	%>

	<h1>タスク削除完了画面</h1>
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
		<td><%= memo%></td>
	</tr>
	</table>
	
	<br>
	<h3>以上のデータを削除しました</h3>
	<br>

	<form action="menu.jsp" method="GET">
	<input type="submit" value="メニューに戻る">
	</form>
	
</body>
</html>