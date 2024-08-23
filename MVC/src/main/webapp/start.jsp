<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%
		// MVC 프로젝트의 시작지점
		// 원하는 주소로 페이지 이동
		// ** 절대로 jsp파일 실행X (단, start.jsp 빼고)	
		// ** 주소줄에도 .jsp주소는 표시x
		//    (주소에 .jsp주소가 있을경우 잘못된 코드)
		// http://localhost:8088/MVC/test.me
		
		// response.sendRedirect("http://localhost:8088/MVC/test.me");
		// response.sendRedirect("./MemberJoin.me");
		// response.sendRedirect("./MemberLogin.me");
		// response.sendRedirect("./Main.me");
		// response.sendRedirect("./BoardWrite.bo");
		response.sendRedirect("./BoardList.bo");
	%>
</body>
</html>