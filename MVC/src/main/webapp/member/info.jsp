<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		/member/info.jsp
	</h1>
	
<!-- 	$ {dto } -->
	<!-- 7행 2열 -->
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>${dto.id}</td>	
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${dto.pw}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${dto.name}</td>	
		</tr>
		<tr>
			<th>성별</th>
			<td>${dto.gender}</td>	
		</tr>
		<tr>
			<th>이메일</th>
			<td>${dto.email}</td>	
		</tr>
		<tr>
			<th>나이</th>
			<td>${dto.age}</td>
		</tr>
		<tr>
			<th>회원가입 날짜</th>
			<td><fmt:formatDate value="${dto.regdate }"/> </td>	
		</tr>
	</table>
	
	<h3><a href="./Main.me">메인페이지로 이동...</a></h3>


</body>
</html>