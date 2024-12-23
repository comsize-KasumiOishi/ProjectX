<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" >
<title>ヘッダー用</title>
</head>
<body>
<% 
String headUserName = (String)session.getAttribute("userName");
int alertCount = (int)session.getAttribute("alert");
%>
<h3>ログインユーザー名:<%=headUserName %></h3>
<% 
if(alertCount == 0){
%>
<h3>期限の迫っているタスクはありません</h3>
<br>
<%}else{ %>
<h3>⚠期限の近いタスクが<%=alertCount %>件あります！</h3>
<br>
<%} %>

</body>
</html>