<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
<!DOCTYPE html>
<!-- ヘッダー画面 -->
<!-- @author 坂上 -->
<html>
<head>
<meta charset="UTF-8">
<title>ヘッダー用</title>
</head>
<body>
<% 
String headUserName = (String)session.getAttribute("userName");
int alertCount = (int)session.getAttribute("alert");
%>
<h1>ログインユーザー名:<%=headUserName %></h1>
<br>
<% 
if(alertCount == 0){
%>
<h3>期限の迫っているタスクはありません</h3>

<%}else{ %>
<h3>⚠期限の近いタスクが<%=alertCount %>件あります！</h3>

<%} %>

</body>
</html>