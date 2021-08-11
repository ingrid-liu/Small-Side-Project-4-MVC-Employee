<%--
  Created by IntelliJ IDEA.
  User: ingridliu
  Date: 8/9/21
  Time: 11:22 PM
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

    <%-- js code statement   --%>
    <script type="text/javascript">
    <%-- add 'single click event' to the verification code img--%>
        $(function () {
            $('#codeImg').click(function () {
                // get the codImg object
                var img=$('#codeImg');
                // modify scr attribution value, but not the uri value
                img.attr('src', img.attr('src')+'?'+Math.random())      //original value:img.attr("src")

            })
        })




    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span><b>Payroll system dev by Ingrid and Ryan</b></span>
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
                <%--  loading the verification code: generate a dynamic verfication img (Servlet)--%>
                <%--  click image->change veri code: browser will automatically generated new tag of the resource(so does url) --%>
                <span><input name="" type="text" value="verification" onclick="JavaScript:this.value=''"/></span>
                    <cite>
                        <img id="codeImg" style="cursor:pointer" src="codeServlet" alt="" width="115px" height="46px">       <%--  call servlet to get the new code --%>
                        <%-- in head use jquery to set the single click event; stype: use css to change cursor as a hand   --%>
                    </cite>
            </li>
            <li><input name="" type="button" class="loginbtn" value="Login"  onclick="javascript:window.location='main.html'"  /><label><input name="" type="checkbox" value="" checked="checked" />Remember me</label><label><a href="#">Forget Password？</a></label></li>
        </ul>


    </div>

</div>





</body>

</html>

