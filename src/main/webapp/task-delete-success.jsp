<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.Date,model.entity.TaskCategoryUserStatusBean,java.util.List"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
<!DOCTYPE html>
<!-- タスク削除完了画面 -->
<!-- @author 坂上 -->
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>タスク削除完了画面</title>
</head>
<body>
	<%
	//詳細画面に表示したBean型を取得
	TaskCategoryUserStatusBean tcusbean = (TaskCategoryUserStatusBean) session.getAttribute("detail");

	int taskId = tcusbean.getTaskId();
	String taskName = tcusbean.getTaskName();
	String categoryName = tcusbean.getCategoryName();
	Date limitDate = null;
	if(tcusbean.getLimitDate() != null){
		limitDate = tcusbean.getLimitDate();
	}
	String userName = tcusbean.getUserName();
	String statusName = tcusbean.getStatusName();
	String memo = null;
	if (tcusbean.getMemo() == null) {
		memo = "";
	} else {
		memo = tcusbean.getMemo();
	}
	%>

	<h1>タスク削除完了画面</h1>
	<hr>

	<table border>
		<tr>
			<td>タスクID</td>
			<td><%=taskId%></td>
		</tr>
		<tr>
			<td>タスク名</td>
			<td><%=taskName%></td>
		</tr>
		<tr>
			<td>カテゴリー名</td>
			<td><%=categoryName%></td>
		</tr>
		<tr>
			<td>期限</td>
			<% 
			if(limitDate == null){
			%>
			<td>無期限</td>
			<%}else{ %>
			<td><%=limitDate%></td>
			<%} %>
		</tr>
		<tr>
			<td>ユーザー名</td>
			<td><%=userName%></td>
		</tr>
		<tr>
			<td>ステータス名</td>
			<td><%=statusName%></td>
		</tr>
		<tr>
			<td>メモ</td>
			<td><%=memo%></td>
		</tr>
	</table>

	<br>
	<h3>以上のデータを削除しました</h3>
	<br>

	<form action="menu.jsp" method="GET">
		<input type="submit" id="button" value="メニューに戻る">
	</form>

</body>
</html>