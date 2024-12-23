<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,java.util.List,model.dao.TaskUserCommentDAO,model.entity.TaskUserCommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>コメント削除成功画面</title>
</head>
<body>
	<h2>コメント削除成功画面</h2>
	<%--CommentDeleteServletのdoGetメソッドでセッションスコープに設定した
	コメント情報が格納されたリストを取得 --%>
	<%
	List<TaskUserCommentBean> commentList = (List<TaskUserCommentBean>) session.getAttribute("commentDeleteList");
	for (TaskUserCommentBean tcubean : commentList) {
	%>
	<table border="1">
		<tr>
			<td><b>投稿者:<%=tcubean.getUserName()%>>
			</b></td>
			<td>投稿日:<%=tcubean.getUpdateDateTime()%></td>
		</tr>
		<tr>
			<td colspan="2"><%=tcubean.getComment()%></td>
		</tr>
	</table>
	<%
	}
	%>
	<h3>以上のコメントの削除に成功しました</h3>
	<form action="TaskListServlet" method="GET">
		<input type="submit" id="button" value="タスク一覧表示画面">
	</form>
</body>
</html>