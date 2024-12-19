<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.Date,model.entity.TaskCategoryUserStatusBean,java.util.List"%>
<!DOCTYPE html>
<!-- タスク削除失敗画面 -->
<!-- @author 坂上 -->
<html>
<head>
<meta charset="UTF-8">
<title>タスク削除失敗画面</title>
</head>
<body>
	<% 
	int commentCheck  = (int)session.getAttribute("check");
	if(commentCheck == 0){
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
		if(tcusbean.getMemo() == null){
			memo = "";
		}else{
			memo = tcusbean.getMemo();
		}
				
	%>

	<h1>タスク削除失敗画面</h1>
	<hr>

	<h3>以下のデータを削除できませんでした。</h3>
	<br>

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

	<form action="TaskListServlet" method="get">
		<input type="submit" value="一覧に戻る">
	</form>
<%
}else{ 

%>
<h3>タスクにコメントが投稿されているのでコメントを削除してから削除をしてください</h3>
<table>
<td>
<form action="TaskListServlet" method="get">
		<input type="submit" value="一覧に戻る">
	</form>
	</td>
	</table>

<%

}
%>
</body>
</html>