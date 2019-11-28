<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../includes/header.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>

  <!-- Page Content -->
  <div class="container">

    <div class="row">
	
	<%@include file="../includes/sider.jsp" %>
	
	<div>
		<form action="/chat/room" method="post">
			<input type="text" name="name" id="name">	
			<button type="submit">방 만들기</button>
		</form>
	</div>
	
	<div>
		<c:forEach items="${list}" var="room">
			<tr>	
				<td><a href="/chat/room/${room.roomId}"><c:out value="${room.name}"/></a></td>
			</tr>
		</c:forEach>
	</div>
	<div id="data">
	</div>

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->
  
  
<%@include file="../includes/footer.jsp"%>
