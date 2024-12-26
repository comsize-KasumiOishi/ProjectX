<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, model.entity.TaskCategoryUserStatusBean, model.entity.TaskUserCommentBean, java.util.Date, java.time.LocalDate,java.time.ZoneId"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap" rel="stylesheet">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>タスク一覧画面</title>
</head>
<body>
	<!-- ページタイトル -->
	<h1>タスク一覧</h1>
	<hr>
	<br>
	<br>
	<!-- メニュー画面遷移ボタン -->
	<div class="btn btn-malformation">
	<form action="menu.jsp" method="POST">
		<input type="submit" id="button" value="メニュー画面へ">
	</form>
	</div>
	<!-- タスク一覧表示用の値をセッションから取得 -->
	<%
	List<TaskCategoryUserStatusBean> taskList = (List) session.getAttribute("taskList");
	List<TaskUserCommentBean> commentCounts = (List) session.getAttribute("commentCounts");
	%>

	
	<!-- タスク一覧生成用テーブル -->
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

		// taskListを繰り返し処理
			for (TaskCategoryUserStatusBean task : taskList) {
		// taskの情報を表示
		%>
		<tr>
			<!-- タスク詳細ボタン -->
			<td>
				<form action="TaskDetailServlet " method="GET">

					<input type="hidden" name="taskId" value="<%=task.getTaskId()%>">
					<div class="btn_07"><input type="submit" name="button" value="詳細" id ="detailbutton"
						style="background-color: #eaf4fc;  color: #1569EF; width: 100%; height: 100%;"></div>

				</form>
			</td>
			<!-- タスク名 -->
			<td><div><%=task.getTaskName()%></div></td>
			<!-- タスクカテゴリー -->
			<td><div><%=task.getCategoryName()%></div></td>
			<!-- タスク期限 -->
			<td>
				<%
				if (task.getLimitDate() == null) {

					String blank = "無期限";
				%>
				<p><%=blank%></p> <%
				 } else {
 				Date limitdate = task.getLimitDate();
 				%>
				<p><%=limitdate%></p> <%
				 }
				 %>
			</td>
			<!-- タスク担当者 -->
			<td><div><%=task.getUserName()%></div></td>
			<!-- タスクステータス -->
			<td><div><%=task.getStatusName()%></div></td>
			<!-- メモ -->
			<td>
				<div class="display-limit">
					<%
					if (task.getMemo() == null) {
						task.setMemo("");
						task.getMemo();
					} else {
						String memo = task.getMemo();
					%>
					<%=memo%>
					<%
					}
					%>
				</div>
			</td>
			<!-- コメント件数 -->
			<td>
				<%
			boolean comment = false;
			for(TaskUserCommentBean tucbean : commentCounts){
				if(tucbean.getTaskId() == task.getTaskId()){
					 comment = true;
					%> <%=tucbean.getcommentCount() %> <% 
				}
				}
				
			if(!(comment)){
				%>
				0
			<% }%>
			
			</td>


			<%
			}
			%>
		<!-- タスクが1件も登録されていない場合 -->
		<% 
		} else {
			%>
		</tr>
	</table>
	<p>タスクは未登録です</p>
		<%
			}
			%>
	
<!-- CSSの設定 -->
<style>
h1{
   font-family: "Zen Maru Gothic", serif;
  font-weight: 600;
  font-style: normal;
}
</style>
</body>
</html>