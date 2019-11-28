<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


      <div class="col-lg-3">
      
      <div>

		<sec:authorize access="isAnonymous()">
			<a href="/user/signin">로그인</a><br/>
			<a href="/user/new">회원가입</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<%@ include file="../user/success.jsp" %>
		</sec:authorize>

      	
      </div>

        <h1 class="my-4">Shop Name</h1>
        <div class="list-group">
          <a href="/chat/room" class="list-group-item">Chat</a>
          <a href="#" class="list-group-item">Category 2</a>
          <a href="#" class="list-group-item">Category 3</a>
        </div>

      </div>