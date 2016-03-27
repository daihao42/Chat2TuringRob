 	<jsp:include page="__header__.jsp"></jsp:include>   
 	
    <title>主页</title>
    <link rel="stylesheet" href="./include/css/style.css">
    <script type="text/javascript">
<!--

//-->
/*
	$(function(){
    		$("#submit").click(function(){
    			if($(".form-control").val() == ""){return;}
				var say = $("<div></div>");
				say.attr('class','alert alert-info');
				say.attr('role','alert');
				var myDate = new Date();
				var mytime = myDate.toLocaleString( );       //获取当前时间
				var time = '<span style="color:gray;float:right;">'+mytime+'</span>';
				say.html($(".form-control").val()+time);
				$("#chatting").append(say);
				//清空输入框
				$(".form-control").val("");
       			$.ajax({
       				type:"post",
       				url:"auth",
       				data:{	email:$("#email").val(),
       						password:$("#password").val(),
       				},
       				dataType:"text",
       				success:function(data){
            			
            			}		
       });
    });
});
*/

$(function(){
		$("#submit").click(function(){
			if($(".form-control").val() == ""){return;}
			var li = $("<li></li>");
			li.html("hahahah");
			$(".chat-thread").append(li);
			//清空输入框
			$(".form-control").val("");
		});
	});
</script>
    
  </head>
  
  <body>
  <div class="container">
    
    
       <div id="convo">  
			<ul class="chat-thread">
	
			</ul>
		</div>

	<form class="chat-window">
	<input class="chat-window-message" name="chat-window-message" type="text" autocomplete="off" autofocus />
</form>

    <div class="comment">
		<span class="label label-default">对话框：</span>
		<p></p>
		<form>
				<textarea name="body" wrap="soft" class="form-control"  placeholder="说点什么吧" rows=4></textarea>
				<p></p>
				<input type="button" class="btn btn-success" value="发送" align="right" id="submit"></input>
			</form>
		</div>
	</div>
  </body>
</html>
