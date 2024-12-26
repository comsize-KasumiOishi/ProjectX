<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
<!DOCTYPE html>
<!-- タスク編集失敗画面 -->
<!-- @author 坂上 -->
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>タスク編集失敗</title>
</head>
<body>
<h1>タスク編集失敗画面</h1>
<hr>
<h2>タスクの編集に失敗しました</h2>
<form action="TaskListServlet" method="get">
<input type="submit" id="button" value="一覧に戻る">
</form>
</body>
</html>