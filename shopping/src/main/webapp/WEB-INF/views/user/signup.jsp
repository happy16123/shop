<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	
	<form action="/user/new" method="post">
		<input type="text" name="id" placeholder="아이디 입력">
		<input type="password" name="password" placeholder="비밀번호 입력">
		<input type="text" name="name" placeholder="이름 입력">
		<input type="text" name="email" placeholder="이메일 입력">
		<input type="text" name="address" placeholder="주소 입력">
		<input type="submit" value="submit">
	</form>

</body>
</html>