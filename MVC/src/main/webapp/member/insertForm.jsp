<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<h1>member/insertForm.jsp (MVC)</h1>
	
	<h2>회원가입 페이지</h2>
	
	<fieldset>
		<legend>회원정보 입력</legend>
		<form action="./MemberJoinAction.me" method="post" >
			아이디 : <input type="text" name="id"> <br>
			비밀번호 : <input type="text" name="pw"> <br>
			이름 : <input type="text" name="name"> <br>
			성별 : <input type="radio" name="gender" value="남"> 남
				<input type="radio" name="gender" value="여"> 여 <br>			
			나이 : <input type="number" name="age"> <br>
			이메일 : <input type="email" name="email">
			
			<hr>
			<input type="submit" value="제출">
		</form>
	</fieldset>

</body>
</html>