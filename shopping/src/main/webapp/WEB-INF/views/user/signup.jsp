<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../includes/header.jsp" %>
  <!-- Page Content -->
  <div class="container">

    <div class="row">
	
	<%@include file="../includes/sider.jsp" %>
	
	<form action="/user/new" method="post">
		<div class="form-group">
			<label for="id">아이디</label>
			<input type="text" class="form-control" id="id" name="id" placeholder="아이디 입력">
			<button type="button" class="btn btn-info" id="idCheckBtn">아이디 중복확인</button>
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호"><br/>
			<label for="passwordCheck">비밀번호 확인</label>
			<input type="password" class="form-control" id="passwordCheck" name="passwordCheck" placeholder="비밀번호 확인"><br/>
			<div class="alert alert-success" id="alert-success">비밀번호가 일치합니다</div>		
			<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다</div>					
		</div>
		<div class="form-group">
			<label for="name">이름</label>
			<input type="text" class="form-control" id="name" name="name" placeholder="이름 입력">
		</div>
		<div class="form-group">
			<label for="email">이메일</label>
			<input type="text" class="form-control" id="email" name="email" placeholder="이메일 입력">
			<button type="button" class="btn btn-info" id="certMailBtn">인증번호 보내기</button>
			<div class="form-group" id="mailCheckDiv">
				<label for="certNum">인증번호 확인</label>
				<input type="text" class="form-control" id="certNum">
				<button type="button" class="btn btn-info" id="certCheckBtn">확인하기</button>
			</div>
		</div>
		<div class="form-group">
			<label for="address">주소</label>
			<input type="text" class="form-control" id="address" name="address" placeholder="주소 입력">
		</div>
		<input type="submit" class="btn btn-success" value="submit">
	</form>

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->
  
  
<%@include file="../includes/footer.jsp"%>

<script>
	$(document).ready(function(){
		
		const mailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		const alert_success = $("#alert-success");
		const alert_danger = $("#alert-danger");
		const mailCheckDiv = $("#mailCheckDiv");
		
		let mailExp = false;
		let mailCert = false;
		let passwordCheck = false;
		let idCheck = false;
		let certNum;
		
		
		alert_success.hide();
		alert_danger.hide();
		mailCheckDiv.hide();
		
		$("#certMailBtn").on("click", function(){
			const receiver = $("input[name=email]").val();
			mailCheckDiv.show();
			
			if(mailExp === true){
				$.ajax({
					type : "GET",
					url : "/mail/" + receiver + "/certification",
					success : function(result){
						certNum = result.content;
					},
					error : function(xhr, status, error){
						console.log(xhr);
					}
				});
			} else {
				alert("올바르지 않는 이메일 주소입니다");
			}
		});
		
		$("#email").keyup(function(){
			let mail = $("#email").val();
			mailRegExp.test(mail) === true ? mailExp = true : mailExp = false;
		});
		
		$("#certCheckBtn").on("click", function(){
			if(certNum == $("#certNum").val()){
				alert("이메일 인증이 되었습니다");
				$("#mailCheckDiv").hide();
				$("#certMailBtn").hide();
				$("#email").prop("readonly", true);
			} else{
				alert("인증번호가 맞지않습니다");
			}
		});
		
		$("#idCheckBtn").on("click", function(){
			const id = $("#id").val();
			
			$.ajax({
				type : "GET",
				url : "/user/new/" + id,
				success : function(result){
					if(result.check === 0){
						alert("아이디 사용이 가능합니다");
						idCheck = true;
					} else{
						alert("이미 사용중인 아이디입니다");
						idCheck = false;
					}
				},
				error : function(xhr, status, error){
					console.log(xhr);
				}
			});
		});
		
		
		$("#passwordCheck").keyup(function(){
			passCheck();
		});
		
		$("#password").keyup(function(){
			passCheck();
		});
		
		function passCheck(){
			const pass1 = $("#password").val();
			const pass2 = $("#passwordCheck").val();
			
			if(pass1 === pass2){
				passwordCheck = true;
				alert_success.show();
				alert_danger.hide();
			} else {
				passwordCheck = false;
				alert_success.hide();
				alert_danger.show();				
			}
		}
		
	});
</script>