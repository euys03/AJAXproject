<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 목록</title>
	</head>
	<body>
		<a href="/board/register">글쓰기</a>
		<h1 align="center">게시글 목록</h1>
		<br><br>
		<table align="center" border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>첨부파일</th>
			</tr>
			<c:forEach items="${bList}" var="board" varStatus="i">
				<tr>
					<td>${i.count }</td>
					<c:url var="bDetail" value="/board/detail">
						<c:param name="boardNo" value="${board.boardNo }"></c:param>
					</c:url>
					<td><a href="/board/detail/${board.boardNo }">${board.boardTitle }</a></td>
					<%-- <td><a href="${bDetail }">${board.boardTitle }</a></td> --%>
					<%-- <td>${board.boardTitle }</td> --%>
					<td>${board.boardWriter }</td>
					<td>${board.bCreateDate }</td>
					<td>${board.boardCount }</td>
					<td>X</td>
				</tr>
			</c:forEach>
		</table>
	</body>

</html>