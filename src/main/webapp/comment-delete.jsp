<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,java.util.List,model.dao.TaskUserCommentDAO,model.entity.TaskUserCommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除確認画面</title>
</head>
<body>
	<h1>コメント削除確認画面</h1>

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

	<h2>以上のコメントを削除しますか？</h2>
	<form action="CommentDeleteServlet" method="POST">
		<input type="submit" value="削除">
	</form>
	<form action="task-detail.jsp">
		<input type="submit" value="タスク詳細画面に戻る">
	</form>
</body>
</html>