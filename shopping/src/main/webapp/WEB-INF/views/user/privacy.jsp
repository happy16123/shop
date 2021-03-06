<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
	<form id="checkForm">
		<input type="password" name="password" placeholder="비밀번호 입력">
	</form>
	<button id="checkBtn">확인</button>
	
	<div id="dataDiv">
		
	</div>
</body>

<script>
	$(document).ready(function(){
			
		$("#checkBtn").on("click", function(){
			const password = $("input[name=password]").val();
			alert(password);
			$.ajax({
				type : "POST",
				url : "/user/privacy",
				contentType : "application/json; charset=UTF-8",
				data : JSON.stringify({"password" : password}),
				success : function(result){
					console.log(result);
					makeInfo(result);
				},
				error : function(xhr, status, error){
					alert("에러");
				}
			});
		});
		
		function makeInfo(data){
			console.log(data);
			let str = "";
			str += "이름 : <input type='text' name='name' value='" + data.name + "'><br>";
			str += "이메일 : <input type='text' name='email' value='" + data.email + "'><br>";
			str += "주소 : <input type='text' name='address' value='" + data.address + "'>";
			
			$("#dataDiv").append(str);
		}
		
	});
</script>

  

</html>