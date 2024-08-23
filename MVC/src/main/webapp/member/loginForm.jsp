<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<h1> member/loginForm.jsp (MVC) </h1>
	
	<h2> 로그인 페이지 </h2>
	
	<fieldset>
		<form action="./MemberLoginAction.me" method="post">
			<!-- 이전페이지 저장 -->
			<input type="hidden"  name="oldURL" value="<%=request.getParameter("oldURL") %>">
			아이디 : <input type="text" name="id"> <br>
			비밀번호 : <input type="password" name="pw"> <br>
			<hr>
			<input type="submit" value="ㄹㄱㅇ">
			<input type="button" value="ㅎㅇㄱㅇ" onClick="location.href='./MemberJoin.me'">
												  
		</form>
	</fieldset>

</body>
</html>