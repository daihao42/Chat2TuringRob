<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./include/js/dropzone.min.js"></script>
	<link rel="stylesheet" href="./include/css/dropzone.min.css">
	<div id="dropz"></div>
	<script>
    	Dropzone.options.dropz = {
    	maxFilesize: 5, // MB
    	acceptedFiles: ".jpg,.gif,.png",
    	maxFiles: 1,
    	}
	</script>

  </head>
  
  <body>
  
  <form action="upload" method="post"
                enctype="multipart/form-data" class="dropzone"
                id="dropz">
            </form>
            
    <form action="upload" method="post" enctype="multipart/form-data">  
<input type="file" name="file" /> <input type="submit" value="Submit" /></form>  
</body>  
  </body>
</html>
