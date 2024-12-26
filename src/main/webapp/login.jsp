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
<title>ログイン画面</title>
</head>
<body>
<div class="login">
    <div class="login-screen">
      <div class="app-title">
        <h1>ログイン画面</h1>
      </div>
	
	<hr>
	<h3>ユーザIDとパスワードを入力してください</h3>
	
	<form action="LoginServlet" method="POST">
	
	<div class="login-form">
        <div class="control-group">
        <input type="text" id="login" class="login-field" value="" placeholder="ユーザーID" name ="userid" required maxlength="24">
        <label class="login-field-icon fui-user" for="login-name"></label>
        </div>
	
	
	<div class="control-group">
        <input type="password" id="login" class="login-field" maxlength="32" value="" placeholder="パスワード" id="password" name = "password" required>
        <label class="login-field-icon fui-lock" for="login-pass"></label>
        </div>
	
	<div class="btn">
	<input type="submit" value="ログイン" class="btn">
	<input type="reset" value="クリア"class="clearbtn">
	</form>
	
		</div>
	</div>
</div>
</body>
</html>