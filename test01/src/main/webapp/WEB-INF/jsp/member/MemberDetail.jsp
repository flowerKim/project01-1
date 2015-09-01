<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Detail Info</title>

<jsp:include page="/header.jsp"/>

<style>
.photo { padding: 10px; }
table { padding: 10px; }
th { text-align: right; }
input { padding-left: 0 3px 0 3px; color: #666666; }
</style>

<body>

<h1 align='center'>Member Detail Info</h1>
<form action='update.do' method='post' enctype='multipart/form-data'>

<table align='center' style="color:#666666">

<tr>
  <th width='60'>번호</th>
  <td><input size='50' type='text' readonly style="border: 0px" name='no' 
      value ='${member.no}'></td>
</tr>
<tr>
  <th>이름</th>
  <td><input size='22' type='text' name='name' value ='${member.name}'></td>
</tr>  
<tr>
  <th>이메일</th>
  <td><input size='22' type='text' name='email' value='${member.email}'></td>
</tr>
<tr>
  <th>전화번호</th>
  <td><input size='22' type='text' name='tel' value='${member.tel}'></td>
</tr>  
<tr>
  <th>비밀번호</th>
  <td><input size='7' type='password' name='password'></td>
</tr>
<tr>
  <th>등록일</th>
  <td>${member.createDate}</td>
</tr>
<tr>
  <td colspan='2' align='center'><a href='../files/${member.photo}'><img 
     class='photo' src='../files/${member.photo}' height='200'></a></td>
</tr>
<tr><td colspan='2' align='left'>${member.photo}</td></tr>
<tr><td colspan='2' align='left'><input type='file' name='photo1'></td></tr>

<tr>
  <td colspan='2' align='right'><input type='submit' value='변경'> <input 
    type='button' onClick="location.href='delete.do?no=${member.no}'" 
    value='삭제'> <input type ='button' onClick="location.href='list.do'" 
    value='목록'>

</table>
</form>

<jsp:include page="/footer.jsp"/>

</head>


</body>
</html>