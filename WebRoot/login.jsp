<%--
  Created by IntelliJ IDEA.
  User: ingridliu
  Date: 8/5/21
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	 <base href="<%=basePath%>"/>
	    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Welcome to the backend management system</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="js/jquery.js"></script>
	<script src="js/cloud.js" type="text/javascript"></script>
	
	<script language="javascript">
		$(function(){
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    })  
	});  
	
		function changeRandom(){
			//alert("ok");
			//获取图片
			
			//修改图片的地址
			$("#randImg").attr("src","random.jpg?time="+new Date().toLocaleString());
			
		}
	</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>Payroll System</span>
    <ul>
    <li><a href="#">Home</a></li>
    <li><a href="#">Help</a></li>
    <li><a href="#">About</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox loginbox2">
    <form action="servlet/EmployeeServlet?method=login" method="post">
	    <ul>
	    <li>
	    	<input name="empId" type="text" class="loginuser" value="liukaili" onclick="JavaScript:this.value=''"/>
	    	<br/>
	    	${error }
	    </li>
	    <li><input name="password" type="password" class="loginpwd" value="123456" onclick="JavaScript:this.value=''"/></li>
	    <li class="yzm">
	    <span><input name="verifyCode" type="text" value="verification" onclick="JavaScript:this.value=''"/></span><cite> <img src="random.jpg" alt="" id="randImg" onclick="changeRandom()"/> </cite>
	    </li>
	    <li><input name="" type="submit" class="loginbtn" value="Login"  /><label><input name="" type="checkbox" value="" checked="checked" />Remember me</label><label><a href="#">Forget Password？</a></label></li>
	    </ul>
    </form>
    
    </div>
    
    </div>
    
    
   
	
    
</body>

</html>
