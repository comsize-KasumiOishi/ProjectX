<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
 ログイン画面
 @author 大石圭純
 -->
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
	ユーザID
	<input type = "text" maxlength="24" name = "userid" required><br>
	パスワード
	<input type = "password" maxlength="32" id="password" name = "password" required><br>
	<br>
	<input type="submit" value="ログイン">
	<input type="reset" value="クリア">
	</form>
</body>
</html>