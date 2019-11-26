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
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
</head>
<body>
	<input type="text" id="message">
	<input type="button" id="sendBtn" value="보내기">
	<input type="button" id="disconnectBtn" value="disconnect">
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
		
		$("#disconnectBtn").on("click", function(){
			onClose("tester");
		});
	});
	
//	let sock = new SockJS("<c:url value='/chat'/>");
	let sock = new SockJS("/ws");
	let stompClient = Stomp.over(sock);
	let roomId = "${room.roomId}";
	
	stompClient.connect({}, function(frame){
		console.log("connected : " + frame);
		stompClient.send("/app/chat/join", {}, JSON.stringify({"roomId" : roomId, "sender" : "tester", "message" : "tester"}));
		stompClient.subscribe("/topic/chat/room/" + roomId, function(response){
			onMessage(JSON.parse(response.body));
		});
	});
	
	function sendMessage(){
		stompClient.send("/app/chat/message", {}, JSON.stringify({"roomId" : roomId, "sender" : "tester", "message" : $("#message").val()}));
		$("#message").val("");
	}
	
	function onMessage(msg){
		$("#data").append(msg.sender + " : " + msg.message + "<br/>"); 
	}
	
	function onClose(evt){
		stompClient.disconnect();
		$("#data").append(evt + "연결 끊김");
	}
</script>

</html>