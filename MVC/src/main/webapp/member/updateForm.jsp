<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<script type="text/javascript">
	
	function check(){
		if(document.fr.id.value == ""){
// 			alert(" 아이디를 입력하세요! ");		
		}		
	}

</script>
</head>
<body>

	<h1>/member/updateForm.jsp</h1>
	
	<fieldset>
		<legend>회원정보 수정</legend>
		<form action="./MemberUpdateProAction.me" method="post" name="fr" onsubmit="check();">
			아이디 : <input type="text" name="id" value="${dto.id }" readonly="readonly" > <br>
			비밀번호 : <input type="text" name="pw" value="${dto.pw }" > <br>
			이름 : <input type="text" name="name" value="${dto.name }"> <br>
			성별 : <input type="radio" name="gender" value="남"  
			
			<c:if test="${dto.gender.equals('남') }">
						checked="checked"
			</c:if>
			> 남
				<input type="radio" name="gender" value="여" 

					<c:if test="${dto.gender.equals('여') }">
						checked="checked"
					</c:if> 
				 >여 <br>			
			나이 : <input type="number" name="age" value="${dto.age }"> <br>
			이메일 : <input type="email" name="email" value="${dto.email }" readonly="readonly" >
			
			<hr>
			<input type="submit" value="수정">
		</form>
	</fieldset>

</body>
</html>