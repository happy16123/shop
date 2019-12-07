<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../includes/header.jsp" %>
  <!-- Page Content -->
  <div class="container">

    <div class="row">
	
	<%@include file="../includes/sider.jsp" %>
	
	<form action="/user/new" method="post">
		<input type="text" class="form-control" name="id" placeholder="아이디 입력">
		<input type="password" class="form-control" name="password" placeholder="비밀번호 입력">
		<input type="text" class="form-control" name="name" placeholder="이름 입력">
		<input type="text" class="form-control" name="email" placeholder="이메일 입력">
		<button type="button" id="certMail">인증번호 보내기</button>
		<input type="text" class="form-control" name="address" placeholder="주소 입력">
		<input type="submit" class="btn btn-success" value="submit">
	</form>

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->
  
  
<%@include file="../includes/footer.jsp"%>

<script>
	$(document).ready(function(){
		$("#certMail").on("click", function(){
			const receiver = $("input[name=email]").val();
			$.ajax({
				type : "POST",
				url : "/mail/certification",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify({"receiver" : receiver}),
				success : function(result){
					console.log(result);
				},
				error : function(xhr, status, error){
					console.log(xhr);
				}
			});
		});
		
	});
</script>