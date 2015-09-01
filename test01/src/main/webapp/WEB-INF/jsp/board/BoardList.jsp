<%@page import="net.bitacademy.java72.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title>Board</title>

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

<h1 align='center'>Free Board(JSP with EL, JSTL)</h1>
<table align='center' style="color:#666666">
	<tr>
		<td colspan='2' align='left'><a href='../member/list.do'>- 회원 목록 보기</a></td>
		<td colspan='2' align='right'><a href='form.html'>- 글쓰기</a></td></tr>
	<tr>
		<th width='40'>번호</th>
		<th width='300'>제목</th>
		<th width='90'>등록일</th>
		<th>조회수</th>
	</tr>
<%-- $표시 이런거를 pageContext에서 꺼내 쓰는데 이렇게 하면
      보드 자체를 $로 가지고 올 수가 있다. 로컬 변수를 가져오기 위해서. 
      꺼내서 담고 꺼내서 담고.
      EL 에서 꺼내서 쓰기 위해서 담는 거임.
--%>
<c:forEach var="board" items="${boards }">
  <tr>
  <td align='center'>${board.no}</td>
      <td><a href='detail.do?no=${board.no}'>${board.title}</a></td>
      <td align='center'>${board.createDate}</td>
      <td align='center'>${board.viewCount}</td></tr>
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