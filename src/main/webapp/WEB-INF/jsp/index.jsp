	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta charset='UTF-8'><meta name="robots" content="noindex"><link rel="canonical" href="http://codepen.io/clintioo/pen/HAkjq" />
	<title>聊天</title>

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="./include/bootstrap/css/bootstrap.min.css">

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
<style class="cp-pen-styles">@import "http://fonts.googleapis.com/css?family=Noto+Sans";
body {
  padding: 0;
  margin: 0;
  background: -moz-linear-gradient(-45deg, #183850 0, #183850 25%, #192C46 50%, #22254C 75%, #22254C 100%);
  background: -webkit-linear-gradient(-45deg, #183850 0, #183850 25%, #192C46 50%, #22254C 75%, #22254C 100%);
  background-repeat: no-repeat;
  background-attachment: fixed;
}

::-webkit-scrollbar {
  width: 10px;
}

::-webkit-scrollbar-track {
  border-radius: 10px;
  background-color: rgba(25, 147, 147, 0.1);
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-color: rgba(25, 147, 147, 0.2);
}

.chat-thread {
  margin: 24px auto 0 auto;
  padding: 0 20px 0 0;
  list-style: none;
  overflow-y: scroll;
  overflow-x: hidden;
}

.chat-thread li {
  position: relative;
  clear: both;
  display: inline-block;
  padding: 16px 40px 16px 20px;
  margin: 0 0 20px 0;
  font: 16px/20px 'Noto Sans', sans-serif;
  border-radius: 10px;
  background-color: rgba(25, 147, 147, 0.2);
}

/* Chat - Avatar */
.chat-thread li:before {
  position: absolute;
  top: 0;
  width: 50px;
  height: 50px;
  border-radius: 50px;
  content: '';
}

/* Chat - Speech Bubble Arrow */
.chat-thread li:after {
  position: absolute;
  top: 15px;
  content: '';
  width: 0;
  height: 0;
  border-top: 15px solid rgba(25, 147, 147, 0.2);
}

li.chat_to {
  animation: show-chat-odd 0.15s 1 ease-in;
  -moz-animation: show-chat-odd 0.15s 1 ease-in;
  -webkit-animation: show-chat-odd 0.15s 1 ease-in;
  float: right;
  margin-right: 80px;
  color: #0AD5C1;
}

li.chat_to:before {
  right: -80px;
  background-image: url(data:image/jpeg;base64,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);
  }

li.chat_to:after {
  border-right: 15px solid transparent;
  right: -15px;
}

li.chat_from {
  animation: show-chat-even 0.15s 1 ease-in;
  -moz-animation: show-chat-even 0.15s 1 ease-in;
  -webkit-animation: show-chat-even 0.15s 1 ease-in;
  float: left;
  margin-left: 80px;
  color: #0EC879;
}

li.chat_from:before {
  left: -80px;
  background-image: url(data:image/png;base64,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);
  }


li.chat_from:after {
  border-left: 15px solid transparent;
  left: -15px;
}

.chat-window {
  position: fixed;
  bottom: 18px;
}

.chat-window-message {
  width: 100%;
  height: 48px;
  font: 32px/48px 'Noto Sans', sans-serif;
  background: none;
  color: #0AD5C1;
  border: 0;
  border-bottom: 1px solid rgba(25, 147, 147, 0.2);
  outline: none;
}

/* Small screens */
@media all and (max-width: 767px) {
  .chat-thread {
    width: 90%;
    height: 260px;
  }

  .chat-window {
    left: 5%;
    width: 90%;
  }
}
/* Medium and large screens */
@media all and (min-width: 768px) {
  .chat-thread {
    width: 50%;
    height: 320px;
  }

  .chat-window {
    left: 25%;
    width: 50%;
  }
}
@keyframes show-chat-even {
  0% {
    margin-left: -480px;
  }
  100% {
    margin-left: 0;
  }
}
@-moz-keyframes show-chat-even {
  0% {
    margin-left: -480px;
  }
  100% {
    margin-left: 0;
  }
}
@-webkit-keyframes show-chat-even {
  0% {
    margin-left: -480px;
  }
  100% {
    margin-left: 0;
  }
}
@keyframes show-chat-odd {
  0% {
    margin-right: -480px;
  }
  100% {
    margin-right: 0;
  }
}
@-moz-keyframes show-chat-odd {
  0% {
    margin-right: -480px;
  }
  100% {
    margin-right: 0;
  }
}
@-webkit-keyframes show-chat-odd {
  0% {
    margin-right: -480px;
  }
  100% {
    margin-right: 0;
  }
}
</style></head><body>

 <!--导航栏-->
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href=".">Daisuke</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav pull-right">
            <li><a href="./logout"><i class="glyphicon glyphicon-log-out"></i> 登出</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
   <br />
    <br />
<br />

<h2 align="center" style="color:white;">For Chatting</h2>
<ul class="chat-thread">
	<li class="chat_from">说点什么吧</li>
</ul>

<form class="chat-window">
	<input class="chat-window-message" name="chat-window-message" type="text" autocomplete="off"  />
</form>
<script src='//assets.codepen.io/assets/common/stopExecutionOnTimeout.js?t=1'></script><script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script>var sendChannel, receiveChannel, chatWindow = document.querySelector('.chat-window'), chatWindowMessage = document.querySelector('.chat-window-message'), chatThread = document.querySelector('.chat-thread');
createConnection();
chatWindow.onsubmit = function (e) {
    e.preventDefault();
    sendData();
    return false;
};
function createConnection() {
    var servers = null;
    if (window.mozRTCPeerConnection) {
        window.localPeerConnection = new mozRTCPeerConnection(servers, { optional: [{ RtpDataChannels: true }] });
    } else {
        window.localPeerConnection = new webkitRTCPeerConnection(servers, { optional: [{ RtpDataChannels: true }] });
    }
    try {
        sendChannel = localPeerConnection.createDataChannel('sendDataChannel', { reliable: false });
    } catch (e) {
    }
    localPeerConnection.onicecandidate = gotLocalCandidate;
    sendChannel.onopen = handleSendChannelStateChange;
    sendChannel.onclose = handleSendChannelStateChange;
    if (window.mozRTCPeerConnection) {
        window.remotePeerConnection = new mozRTCPeerConnection(servers, { optional: [{ RtpDataChannels: true }] });
    } else {
        window.remotePeerConnection = new webkitRTCPeerConnection(servers, { optional: [{ RtpDataChannels: true }] });
    }
    remotePeerConnection.onicecandidate = gotRemoteIceCandidate;
    remotePeerConnection.ondatachannel = gotReceiveChannel;
    localPeerConnection.createOffer(gotLocalDescription, function (err) {
    });
}
function sendData() {
    sendChannel.send(chatWindowMessage.value);
    if($(".chat-window-message").val() == ""){return;}
	$.ajax({
       				type:"post",
       				url:"say",
       				timeout : 10000,
       				data:{	say:$(".chat-window-message").val()
       				},
       				dataType:"text",
       				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
       				success:function(data){
            			var li = $("<li></li>");
						li.html(data);
						li.attr("class","chat_from");
						$(".chat-thread").append(li);
						chatThread.scrollTop = chatThread.scrollHeight;
            			}		
       });  
}
function gotLocalDescription(desc) {
    localPeerConnection.setLocalDescription(desc);
    remotePeerConnection.setRemoteDescription(desc);
    remotePeerConnection.createAnswer(gotRemoteDescription, function (err) {
    });
}
function gotRemoteDescription(desc) {
    remotePeerConnection.setLocalDescription(desc);
    localPeerConnection.setRemoteDescription(desc);
}
function gotLocalCandidate(event) {
    if (event.candidate) {
        remotePeerConnection.addIceCandidate(event.candidate);
    }
}
function gotRemoteIceCandidate(event) {
    if (event.candidate) {
        localPeerConnection.addIceCandidate(event.candidate);
    }
}
function gotReceiveChannel(event) {
    receiveChannel = event.channel;
    receiveChannel.onmessage = handleMessage;
    receiveChannel.onopen = handleReceiveChannelStateChange;
    receiveChannel.onclose = handleReceiveChannelStateChange;
}
function handleMessage(event) {
    var chatNewThread = document.createElement('li'), chatNewMessage = document.createTextNode(event.data);
    chatNewThread.appendChild(chatNewMessage);
    chatNewThread.setAttribute("class", "chat_to");
    chatThread.appendChild(chatNewThread);
    chatThread.scrollTop = chatThread.scrollHeight;
    chatWindowMessage.value = '';
}
function handleSendChannelStateChange() {
    var readyState = sendChannel.readyState;
    if (readyState == 'open') {
        chatWindowMessage.disabled = false;
        chatWindowMessage.focus();
        chatWindowMessage.placeholder = '';
    } else {
        chatWindowMessage.disabled = true;
    }
}
function handleReceiveChannelStateChange() {
    var readyState = receiveChannel.readyState;
}
//# sourceURL=pen.js
</script>
</body></html>
