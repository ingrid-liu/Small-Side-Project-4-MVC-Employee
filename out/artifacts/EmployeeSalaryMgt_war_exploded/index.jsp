<%--
  Created by IntelliJ IDEA.
  User: sparkle
  Date: 8/9/21
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Payroll System</title>
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
  </script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



<div id="mainBody">
  <div id="cloud1" class="cloud"></div>
  <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
  <span>Welcome to Payroll System</span>
  <ul>
    <li><a href="#">Home</a></li>
    <li><a href="#">Help</a></li>
    <li><a href="#">About</a></li>
  </ul>
</div>

<div class="loginbody">

  <span class="systemlogo"></span>

  <div class="loginbox loginbox2">

    <ul>
      <li><input name="" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/></li>
      <li><input name="" type="text" class="loginpwd" value="password" onclick="JavaScript:this.value=''"/></li>
      <li class="yzm">
        <span><input name="" type="text" value="verification" onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite>
      </li>
      <li><input name="" type="button" class="loginbtn" value="Login"  onclick="javascript:window.location='main.html'"  /><label><input name="" type="checkbox" value="" checked="checked" />Remember me</label><label><a href="#">Forget Password？</a></label></li>
    </ul>


  </div>

</div>





</body>

</html>
