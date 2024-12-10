<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.entity.TaskCategoryUserStatusBean , java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集画面</title>
</head>
<body>
	<%
	//詳細画面に表示したBean型を取得
	TaskCategoryUserStatusBean tcus = (TaskCategoryUserStatusBean) session.getAttribute("detail");

	//セレクトボックスで表示するためのカテゴリーリストを取得
	List<TaskCategoryUserStatusBean> categoryList = (List<TaskCategoryUserStatusBean>) session.getAttribute("categoryList");
	//セレクトボックスで表示するためのユーザーリストを取得
	List<TaskCategoryUserStatusBean> userList = (List<TaskCategoryUserStatusBean>) session.getAttribute("userList");
	//セレクトボックスで表示するためのステータスリストを取得
	List<TaskCategoryUserStatusBean> statusList = (List<TaskCategoryUserStatusBean>) session.getAttribute("statusList");
	%>
	<h1>タスク編集画面</h1>
	<hr>
	<!-- タスク編集Servletに変更内容を送る -->
	<!-- また、初期値として詳細画面で表示した内容を表示する-->
	<form action="TaskEditServlet" method="post">
		<table border="1">
			<tr>
				<td>タスクID</td>
				<td><%=tcus.getTaskId()%></td>
			</tr>
			<tr>
				<td>タスク名</td>
				<td><input type="text" name="updatetaskname"
					value="<%=tcus.getTaskName()%>"></td>
			</tr>
			<tr>
				<td>カテゴリー名</td>
				<td><select name="updatecategoryid">
						<%
						for (int i = 0; i < categoryList.size(); i++) {
							String categoryName = categoryList.get(i).getCategoryName();
							int categoryId = categoryList.get(i).getCategoryId();
							int repcategoryId = tcus.getCategoryId();
							//カテゴリーリストから詳細画面で表示したカテゴリーを表示する
							if (repcategoryId == categoryList.get(i).getCategoryId()) {
						%>
						<!--セレクトボックスの中は表示はカテゴリー名でカテゴリーIDをServletに送る -->
						<option value="<%=categoryId%>"><%=categoryName%></option>
						<%
							}
						}

						for (int i = 0; i < categoryList.size(); i++) {
						String categoryName = categoryList.get(i).getCategoryName();
						int categoryId = categoryList.get(i).getCategoryId();
						int repcategoryId = tcus.getCategoryId();
						//カテゴリーリストから詳細画面で表示していないカテゴリーを表示する
						if (repcategoryId != categoryList.get(i).getCategoryId()) {
						%>

						<!--セレクトボックスの中は表示はカテゴリー名でカテゴリーIDをServletに送る -->
						<option value="<%=categoryId%>"><%=categoryName%></option>
						<%
							}
						}
						%>
				</select></td>
			</tr>
			<tr>
				<td>期限</td>
				<td><input type="date" name="updatelimitdate" min=""> <!-- minに現在時刻を設定する--></td>
			</tr>
			<tr>
				<td>ユーザー名</td>
				<td><select name="updateuserid">
						<%
						for (int i = 0; i < userList.size(); i++) {
							String userName = userList.get(i).getUserName();
							String userId = userList.get(i).getUserId();
							String repuserId = tcus.getUserId();
							//ユーザーリストから詳細画面で表示したユーザーを表示する
							if (repuserId.equals(userList.get(i).getUserId())) {
						%>
						<!--セレクトボックスの中は表示はカテゴリー名でカテゴリーIDをServletに送る -->
						<option value="<%=userId%>"><%=userName%></option>
						<%
							}
						}
						for (int i = 0; i < userList.size(); i++) {
						String userName = userList.get(i).getUserName();
						String userId = userList.get(i).getUserId();
						String repuserId = tcus.getUserId();
						//ユーザーリストから詳細画面で表示したユーザー以外を表示する
						if (!(repuserId.equals(userList.get(i).getUserId()))) {
						%>
						<!--セレクトボックスの中は表示はカテゴリー名でカテゴリーIDをServletに送る -->
						<option value="<%=userId%>"><%=userName%></option>
						<%
							}
						}
						%>
				</td>
			</tr>
			<tr>
				<td>ステータス名</td>
				<td><select name="updatestatuscode">
						<%
						//ステータスリストから詳細画面で表示したステータスを表示する
						for (int i = 0; i < statusList.size(); i++) {
							String statusName = statusList.get(i).getStatusName();
							String statusCode = statusList.get(i).getStatusCode();
							String repstatusCode = tcus.getUserId();
							if (repstatusCode.equals(statusList.get(i).getStatusCode())) {
						%>
						<!--セレクトボックスの中は表示はステータス名でステータスIDをServletに送る -->
						<option value="<%=statusCode%>"><%=statusName%></option>
						<%
							}
						}
						//ステータスリストから詳細画面で表示したステータス以外を表示する
						for (int i = 0; i < statusList.size(); i++) {
						String statusName = statusList.get(i).getStatusName();
						String statusCode = statusList.get(i).getStatusCode();
						String repstatusCode = tcus.getUserId();
						if (!(repstatusCode.equals(statusList.get(i).getStatusCode()))) {
						%>
						<!--セレクトボックスの中は表示はステータス名でステータスIDをServletに送る -->
						<option value="<%=statusCode%>"><%=statusName%></option>
						<%
							}
						}
						%>
				</td>
			</tr>
			<tr>
				<td>メモ</td>
				<td><input type="text" name="updatememo"
					value="<%=tcus.getMemo()%>"></td>
			</tr>
		</table>
		<table>
			<input type="submit" value="変更する">
			<input type="reset" value="すべてクリアにする">
			</form>
			<form action="TaskListServlet" action="get">
				<input type="submit" value="一覧に戻る">
			</form>
		</table>
</body>
</html>