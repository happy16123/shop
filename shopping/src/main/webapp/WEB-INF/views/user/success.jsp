<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
        
     <sec:authentication property="principal" var="username" />
     ${username}
	<a href="/user/privacy">개인정보</a><br>
	<a href="/user/logout">로그아웃</a>
		