<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>コメント登録成功画面</title>
</head>
<body>
	<h1>コメント登録成功画面</h1>
	<h2>コメントの登録に成功しました。</h2>
	<form action="TaskListServlet" method="GET">
		<input type="submit" id="button" value="タスク一覧表示画面">
	</form>
</body>
</html>