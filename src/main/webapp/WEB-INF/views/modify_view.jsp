<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>modify_view</h3>
	<form action="/modify" method="post">
	<input type="hidden" name="bid" value="${content_view.bid}" />
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
				<td><input type="text" name="bname" value="${content_view.bname}" /></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="btitle" value="${content_view.btitle}" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="bcontent" rows="10">${content_view.bcontent}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정" /> &nbsp;&nbsp; 
					<a href="list">목록</a> &nbsp;&nbsp; 
					<a href="modify_view">수정</a> &nbsp;&nbsp; 
					<a href="">삭제</a>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>