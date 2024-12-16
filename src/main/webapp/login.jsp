<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h3>ログイン画面</h3>
	<hr>
	<h3>ユーザIDとパスワードを入力してください</h3>
	
	<form action="LoginServlet" method="POST">
	<%--24文字制限も記載する --%>
	ユーザID
	<input type = "text" name = "userid" required><br>
	パスワード
	<input type = "password" id="password" name = "password" required><br>
	<br>
	<input type="submit" value="ログイン">
	<input type="reset" value="クリア">
	</form>
</body>
</html>