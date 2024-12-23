<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>ログイン画面</title>
</head>
<body>
	<h2>ログイン画面</h2>
	<hr>
	<h3>ユーザIDとパスワードを入力してください</h3>
	
	<form action="LoginServlet" method="POST">
	ユーザID
	<input type = "text" id="textbox" maxlength="24" name = "userid" required><br>
	パスワード
	<input type = "password" maxlength="32" id="textbox" name = "password" required><br>
	<br>
	<input type="submit" id="button" value="ログイン">
	<input type="reset" id="button" value="クリア">
	</form>
</body>
</html>