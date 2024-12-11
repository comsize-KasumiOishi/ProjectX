<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="model.entity.TaskCategoryUserStatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク詳細画面</title>
<style>
.txt{
    display: inline-block;
    width: 70%;
    border: 1px solid #999;
    box-sizing: border-box;
    background: #f2f2f2;
    margin: 0.5em 0;
}
</style>
</head>
<body>
<% 
//セッションに入れた詳細画面のBean型を取得
TaskCategoryUserStatusBean tcus = new TaskCategoryUserStatusBean();
tcus = (TaskCategoryUserStatusBean)session.getAttribute("detail");

%>
<h1>タスク詳細画面</h1>
<hr>
 <table border="1">
 <tr>
 <td>タスクID</td>
 <td><%= tcus.getTaskId() %></td>
 </tr>
  <tr>
 <td>タスク名</td>
 <td><%= tcus.getTaskName() %></td>
 </tr>
  <tr>
 <td>カテゴリー名</td>
 <td><%= tcus.getCategoryName() %></td>
 </tr>
 <tr>
 <td>期限</td>
 <% 
 if(tcus.getLimitDate() == null){
 %>
 <td>無期限</td>
 <% 
 }else{
 %>
 <td><%= tcus.getLimitDate() %></td>
 <%
 }
 %>
 </tr>
  <tr>
 <td>担当者</td>
 <td><%= tcus.getUserName() %></td>
 </tr>
 <tr>
 <td>ステータス名</td>
 <td><%= tcus.getStatusName() %></td>
 </tr>
  <tr>
 <td>メモ</td>
 <td><%= tcus.getMemo() %></td>
 </tr>
 </table>

<% 
//現在ログインしているユーザーが詳細画面に表示されている
//タスクの担当者かどうかのチェックを行う
String userName = (String)session.getAttribute("userName");
if(userName.equals(tcus.getUserName())){ 
//ログイン者がタスクの担当者の場合、ボタン押下できる
%>
	<table>	
	<form action="TaskDetailServlet" method="post">
<input type="submit" value="編集" >
</form>
<form action="TaskDeleteServlet" method="post">
<input type="submit" value="削除">
</form>
</table>
<%
}else{
//ログイン者がタスクの担当者ではない場合、ボタンを非活性化する
%>
<table>	
	<form action="TaskDetailServlet" method="post">
<input type="submit" value="編集" disabled>
</form>
<form action="TaskDeleteServlet" method="post">
<input type="submit" value="削除" disabled>
</form>
</table>
<%} %>
<form action="ComentOutServlet" method="post">
<input type="text" name="comment" class="txt" maxlength="100"><br>
<input type="submit" value="コメント投稿">
</form>
</body>
</html>