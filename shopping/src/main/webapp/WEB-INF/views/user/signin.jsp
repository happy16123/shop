<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<h1>${error}</h1>
<form method="post" action="/user/login">
	<div>
		<input type="text" class="form-control" name="username" value="admin">
	</div>
	<div>
		<input type="password" class="form-control" name="password" value="admin">
	</div>
	<div>
		<input type="checkbox" name="remember-me">Remember Me
	</div>
	<div>
		<input type="submit">
	</div>
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
