<%@page import="net.bitacademy.java72.domain.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title>Member</title>

<jsp:include page="/header.jsp"/>

<style>
#paging {
  width: 500px;
  margin: 0 auto;
  padding-top: 15px;
}
</style>

</head><body>

<jsp:include page="/LoginInfo.jsp"/>

<h1 align='center'>Member Info(JSP)</h1>
<table align='center' style="color:#666666">
<tr>
	<td colspan='2' align='left'><a href='../board/list.do'>- 게시판 보기</a></td>
	<td colspan='3' align='right'><a href='form.html'>- 회원 등록</a></td></tr>
	
<tr>
	<th width='30'>번호</th>
	<th width='70'>이름</th>
	<th width='180'>email</th>
	<th width='90'>전화</th>
	<th width='70'>등록일</th>
</tr>

<c:forEach var="member" items="${members}">

<tr>
  <td align='center'>${member.no}</td>
  <td align='center'><a href='detail.do?no=${member.no}'>${member.name}</a></td>
  <td align='center'>${member.email}</td><td align='center'>${member.tel}</td>
  <td align='center'>${member.createDate }</td>
</tr>

</c:forEach>
</table>

<%-- 페이징 처리 --%>
<div id='paging'>
  <div>
    <c:choose>
      <c:when test="${!empty prevPageNo}">
        <a href='list.do?pageNo=${prevPageNo}&pageSize=${pageSize}'>이전</a>
      </c:when>
      <c:otherwise>
        이전
      </c:otherwise>
    </c:choose>
    
    <c:choose>
      <c:when test="${!empty nextPageNo}">
        <a href='list.do?pageNo=${nextPageNo}&pageSize=${pageSize}'>다음</a>
      </c:when>
      <c:otherwise>
        다음
      </c:otherwise>
    </c:choose>
  </div>
</div>
<%-- 페이징 처리 끝 --%>

<jsp:include page="/footer.jsp"/>

</body></html>