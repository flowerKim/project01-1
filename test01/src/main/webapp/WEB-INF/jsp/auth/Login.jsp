<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Login Form</title>
<style>
.main {
  width: 300px;
  margin: 0px auto;
}
table{
  border: 1px solid lightgray;
  padding: 10px;
  font-family: 굴림체;
  font-size: 12px;
  border-radius: 10px;
  border-spacing: 5px 2px;
}
th {
  color: #666666;
  width: 60px;
  font-size: 12px;
  padding-right: 5px;
  text-align: right;
}
input {
  padding-left: 3px;
}
textarea {
  padding-left: 3px;
}
<%
String refererUrl = request.getHeader("Referer");
if (refererUrl != null) {
  request.getSession().setAttribute("refererUrl", refererUrl);
}
%>

</style>
</head>
<body>
<div class='main'>
<h2>로그인</h2>
<form action='login.do' method='post'>
<table style="color:#666666">
<tr>
<th>이메일</th>
<td><input size='13' type='text' name='email' value='${email}'></td>
</tr>
<tr>
<th>비밀번호</th>
<td><input size='13' type='password' name='password'></td>
</tr>
<tr>
<th></th>
<td><input type='checkbox' name='saveEmail'>이메일 기억하기</td>
</tr>
<tr><td colspan='2' align='right'><button type='submit'>로그인</button></td></tr>
</table>
</form>
</div>
</body>
</html>
