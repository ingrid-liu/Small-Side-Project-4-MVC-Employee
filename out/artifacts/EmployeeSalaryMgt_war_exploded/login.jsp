<%--
  Created by IntelliJ IDEA.
  User: ingridliu
  Date: 8/5/21
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

    // focus event for user input verification code
        $(function () {
            $("#userCode").blur(function () {
                // get user input code
                var userCode=$("#userCode").val();
                // make an AJAX request
                    if(userCode==null || userCode=="") {
                        //remind the user that verification code can't be empty
                        $("#messageSpan").html("Please input the verification code.").css("color", "red");
                    }else{
                        $.get("codeCheckServlet",{userCode:userCode}, function (date) {
                            //"true"|"false"
                            //eval() evaluates the expression
                            if(!eval(data)){
                                $("#messageSpan").html("The verification code is incorrect.").css("color", "red");
                            }


                        })
                    }
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
    <%-- Create the notice for invalid verification code input --%>
    <div style="text-align: center">
        <span id=""messageSpan></span>
    </div>


    <div class="loginbox loginbox2">
        <%-- get calling from the servlet --%>
        <form action="userServlet" method="post">

            <ul>
                <li><input name="empid" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/></li>
                <li><input name="password" type="password" class="loginpwd" value="password" onclick="JavaScript:this.value=''"/></li>
                <li class="yzm">
                    <%--  loading the verification code: generate a dynamic verfication img (Servlet)--%>
                    <%--  click image->change veri code: browser will automatically generated new tag of the resource(so does url) --%>
                    <span><input id="userCode" name="" type="text" value="verification" onclick="JavaScript:this.value=''"/></span>
                        <cite>
                            <img id="codeImg" style="cursor:pointer" src="codeServlet" alt="" width="115px" height="46px">       <%--  call servlet to get the new code --%>
                            <%-- in head use jquery to set the single click event; stype: use css to change cursor as a hand   --%>
                        </cite>
                </li>
                <li><input type="submit" class="loginbtn" value="Login" /><label><input name="" type="checkbox" value="" checked="checked" />Remember me</label><label><a href="#">Forget Password？</a></label></li>
            </ul>
        </form>


    </div>

</div>





</body>

</html>

