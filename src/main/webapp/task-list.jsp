<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, model.entity.TaskCategoryUserStatusBean, java.util.Date, java.time.LocalDate,java.time.ZoneId"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>タスク一覧</h1>
	<hr>

	<form action="menu.jsp" method="POST">
		<br> <input type="submit" value="メニュー画面へ">
	</form>

	<%
	List<TaskCategoryUserStatusBean> taskList = (List) session.getAttribute("taskList");
	%>
	<table border=1>
		<tr>
			<th></th>
			<th>タスク</th>
			<th>カテゴリ</th>
			<th>期限</th>
			<th>担当者</th>
			<th>ステータス</th>
			<th>メモ</th>
		</tr>



		<%
			if (taskList != null && !taskList.isEmpty()) {
				
				// taskListを繰り返し処理
				for (TaskCategoryUserStatusBean task : taskList) {
					// taskの情報を表示
			%>
		<tr>
			<td>

				<form action="TaskDetailServlet "method="GET">
				<%System.out.println(task.getTaskId()); %>
					<input type="hidden" name="taskId" value="<%=task.getTaskId()%>">
					<input type="submit" name="button" value="詳細" style="background-color:green;">
					
				</form>
			</td>
			<td><%=task.getTaskName()%></td>
<%System.out.println(task.getTaskName()); %>
			<td><%=task.getCategoryName()%></td>
<%System.out.println(task.getCategoryName()); %>
			<td>
				<%
				//LocalDate nowTime = LocalDate.ofInstant(now.toInstant(), ZoneId.systemDefault());
				if (task.getLimitDate() == null) {
				
 					String blank = "無期限";
 				%> 
 				<%=blank%> 
 				<%
 				}else{
 					Date limitdate = task.getLimitDate();
 				%>
				<p><%= limitdate %></p>
				<% } %>
			</td>
			<td><%=task.getUserName()%></td>
			<td><%=task.getStatusName()%></td>

			<td>
				<%
				if (task.getMemo() == null) {
					task.setMemo("");
					task.getMemo();
				 } else { 
					 String memo = task.getMemo();%> 
					 <%=memo %>
					 <% } %>
			</td>
			<%
			}
			%>

			<%
			}
			%>

		</tr>

	</table>

</body>
</html>