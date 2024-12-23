<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,java.util.List,model.dao.TaskUserCommentDAO,model.entity.TaskUserCommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>コメント削除確認画面</title>
</head>
<body>
	<h2>コメント削除確認画面</h2>

	<table border="1">
		<%--CommentDeleteServletのdoGetメソッドでセッションスコープに設定した
	コメント情報が格納されたリストを取得 --%>
		<%
		List<TaskUserCommentBean> commentList = (List<TaskUserCommentBean>) session.getAttribute("commentDeleteList");
		for (TaskUserCommentBean tcubean : commentList) {
		%>
		<tr>
			<td><b>投稿者:<%=tcubean.getUserName()%>>
			</b></td>
			<td>投稿日:<%=tcubean.getUpdateDateTime()%></td>
		</tr>
		<tr>
			<td colspan="2"><%=tcubean.getComment()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<h3>以上のコメントを削除しますか？</h3>
	<form action="CommentDeleteServlet" method="POST">
		<input type="submit" id="button" value="削除">
	</form>
	<form action="task-detail.jsp">
		<input type="submit" id="button" value="タスク詳細画面に戻る">
	</form>
</body>
</html>