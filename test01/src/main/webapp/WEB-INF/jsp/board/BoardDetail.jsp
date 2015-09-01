<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>

<jsp:include page="/header.jsp"/>

<style>
a { text-decoration: none; }
a:HOVER { color=#666666; }
a:LINK { color=#666666; }
a:VISITED { color=#666666; }
table { border-spacing: 5px 2px; padding:10px; font-family: 굴림체; font-size: 12px; border-radius: 10px; border: 1px solid darkgray; }
th { text-align: right; padding: 5px; }
input { color: #666666; }
textarea { color: #666666; }
</style>
</head>
<body>
<h1 align='center'>상세 보기 with EL</h1>
<form action='update.do' method='post' enctype="multipart/form-data">
<table align='center' style="color:#666666">

<tr>
<th width='60'>번호</th>
<td><input type='text' readonly style="border: 0px" name='no' value ='${board.no}'></td>
</tr>

<tr>
<th>제목</th>
<td><input style="width:369px" type='text' name='title' value ='${board.title}'></td>
</tr>

<tr>
<th>내용</th>
<td><textarea rows='5' cols='50' name='content'>${board.content}</textarea></td>

</tr>
<tr>
<th>파일 첨부</th>
<td>[ <a href='../files/${board.attachFile1}'>${board.attachFile1}</a> ]<input 
      type='file' name='file1'></td></tr>

<tr>
<th>비밀번호</th><td><input size='7' type='password' name='password'></td>
</tr>
<tr>
<th>조회수</th>
<td>${board.viewCount}</td>
</tr>

<tr>
<th>등록일</th>
<td>${board.createDate}</td>
</tr>

<tr>
<td colspan= '2' align='right'><input type ='submit' 
  value = '변경'> <input type ='button' 
  onClick= "location.href='delete.do?no=${board.no}'" 
  value='삭제'> <input type ='button' onClick= "location.href='list.do'" 
  value = '목록'>
</table>
</form>

<jsp:include page="/footer.jsp"/>

</body></html>
