<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, model.entity.TaskCategoryUserStatusBean, model.entity.TaskUserCommentBean, java.util.Date, java.time.LocalDate,java.time.ZoneId"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>タスク一覧</h1>
	<hr>
	<br><br>
	<form action="menu.jsp" method="POST" >
		<input type="submit" value="メニュー画面へ">
	</form>
	<%
	List<TaskCategoryUserStatusBean> taskList = (List) session.getAttribute("taskList");
	%>
	<%
	List<TaskUserCommentBean> commentCounts = (List) session.getAttribute("commentCounts");
	%>
	
	
	
	<table border=1 class="fixed-table">
	
		<tr>
			<th></th>
			<th>タスク</th>
			<th>カテゴリ</th>
			<th>期限</th>
			<th>担当者</th>
			<th>ステータス</th>
			<th>メモ</th>
			<th>コメント件数</th>
		</tr>
		<%
		//タスクリストが空でないときの処理
	if (taskList != null && !taskList.isEmpty()) {
		
		int commentCountForTask = 0;

				
		// taskListを繰り返し処理
		for (TaskCategoryUserStatusBean task : taskList) {
		// taskの情報を表示
		%>
			<tr>
				<td>
				<form action="TaskDetailServlet "method="GET">
				
					<input type="hidden" name="taskId" value="<%=task.getTaskId()%>">
					<input type="submit" name="button" value="詳細" style="background-color: green; width: 100%; height: 100%;">
					
				</form>
				</td>
				
				<td><p><%=task.getTaskName()%></p></td>
	
				<td><p><%=task.getCategoryName()%></p></td>
	
				<td>
				<%
				if (task.getLimitDate() == null) {
				
 					String blank = "無期限";
 				%> 
 				<p><%=blank%></p> 
 				<%
 				}else{
 					Date limitdate = task.getLimitDate();
 				%>
				<p><%= limitdate %></p>
				<% } %>
				</td>
				
				<td><p><%=task.getUserName()%></p></td>
				
				<td><p><%=task.getStatusName()%></p></td>
				
				<td>
				 <p class="omitted-text-2">
				 	<%
					if (task.getMemo() == null) {
						task.setMemo("");
						task.getMemo();
					 } else { 
						 String memo = task.getMemo();%> 
						 <%=memo %>
						 <% } %>
				</p>
				</td>
		<% for (TaskUserCommentBean commentCount : commentCounts) {
			
		    	if (task.getTaskId() == commentCount.getTaskId()) {
		        // task.getTaskId()とcommentCount.getTaskId()が一致した場合の処理
		    %>
		    	<td><%=commentCount.getcommentCount()%></td>
			<% } else {
			%>
				<td><%= commentCountForTask %></td>
			<% } %>
			
		<% 
		}
			
		//タスクリストが空の時の処理
		}
	}else { 
		%>
	
	<p>タスクは未登録です</p>
	
	<%
	}
	%>

			</tr>

	</table>
	
	

<style>

.fixed-table {
    table-layout: fixed;
     width: 100%;
     border-collapse: collapse;
}

.omitted-text-2 {
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2; 
}
</style>

</body>
</html>