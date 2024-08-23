<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>/member/list.jsp (MVC)</h1>
	
	<h2> 관리자 메뉴 - 회원 목록 조회</h2>
	
	${dto }
	<hr>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>패스워드</th>
			<th>이름</th>
			<th>성별</th>
			<th>나이</th>
			<th>이메일</th>
			<th>회원가입 날짜</th>
		</tr>
	<c:forEach var="list"  items="${dto}">
		<tr>
			<td>${list.id}</td>
			<td>${list.pw}</td>
			<td>${list.name}</td>
			<td>${list.gender}</td>
			<td>${list.age}</td>
			<td>${list.email}</td>
			<td>${list.regdate}</td>
		</tr>
	</c:forEach>
	</table>
	
	<h3><a href="./Main.me">메인페이지로.....</a></h3>
	
	
	
	
</body>
</html>