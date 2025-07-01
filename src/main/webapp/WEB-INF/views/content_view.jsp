<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>content_view</h3>
	<table width="500" border="1">
		<tr>
			<td>번호</td>
			<td>${content_view.bid}</td>
		</tr>
		<tr>
			<td>히트</td>
			<td>${content_view.bhit}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${content_view.bname}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${content_view.btitle}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${content_view.bcontent}</td>
		</tr>
		<tr>
			<td>첨부</td>
			<td>
				<c:forEach items="${imgList}" var="imgdto">
				<a href="download?f=${imgdto.rebchgfile}&bid=${content_view.bid}">${imgdto.rebchgfile}</a> <br />
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="list">목록</a> &nbsp;&nbsp;
				<a href="modify_view?bid=${content_view.bid}">수정</a> &nbsp;&nbsp;				
				<a href="delete?bid=${content_view.bid}">삭제</a>&nbsp;&nbsp;
				<a href="reply_view?bid=${content_view.bid}">답변</a>
			</td>
		</tr>
	</table>

</body>
</html>