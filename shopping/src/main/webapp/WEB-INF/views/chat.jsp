<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
</head>
<body>
	<input type="text" id="message">
	<input type="button" id="sendBtn" value="보내기">
	
	<div id="data">
	</div>
</body>

<script>
	$(document).ready(function(){
		$("#sendBtn").on("click", function(){
			sendMessage();
		});	
		
		$("#message").keydown(function(key){
			if(key.keyCode == 13){
				sendMessage();
			}
		});
		
	});
	
//	let sock = new SockJS("<c:url value='/chat'/>");
	let sock = new SockJS("/chat");
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	
	function sendMessage(){
		sock.send($("#message").val());
		$("#message").val("");
	}
	
	function onMessage(msg){
		let data = msg.data;
		$("#data").append(data + "<br/>");
	}
	
	function onClose(evt){
		$("#data").append(evt + "연결 끊김");
	}
</script>

</html>