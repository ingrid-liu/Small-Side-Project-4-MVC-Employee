<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/24 0024
  Time: 上午 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎登录后台管理系统</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>
    <script type="text/javascript">
        //判断登录页面是否为顶层页面，不是则置顶显示
        if(window.top.location!=window.location){
            window.top.location.href=window.location.href;
        }

    </script>
    <script language="javascript">
        $(function(){
            $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            $(window).resize(function(){
                $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            })
        });
    </script>
    <%--声明js代码域--%>
    <script type="text/javascript">
        //声明页面加载事件:给验证码图片增加单击事件
        $(function () {
            $("#codeImg").click(function () {
                //获取验证码图片对象
                var img=$("#codeImg");
                //修改src属性值，但是不修改uri的值
                img.attr("src",img.attr("src")+"?"+Math.random())
            })
        })



        //给验证码输入框增加焦点事件完成验证码的校验
        $(function () {
            $("#userCode").blur(function () {
                //获取用户输入的验证码信息
                var userCode=$("#userCode").val();
                //发起ajax请求
                if(userCode==null || userCode==""){
                    $("#messageSpan").html("验证码不能为空").css("color","red");

                    //将登录按钮置为可选状态
                    $("#submitBtn").prop("disabled",true);
                }else{
                    //发起ajax请求
                    $.get("codeCheckServlet",{userCode:userCode},function (data) {//"true"|"false"
                        $("#messageSpan").html("");


                        if(!eval(data)){
                            $("#messageSpan").html("验证码不正确").css("color","red");
                            //将登录按钮置为可选状态
                            $("#submitBtn").prop("disabled",true);
                        }else{
                            //将登录按钮置为可选状态
                            $("#submitBtn").prop("disabled",false);
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
    <span>欢迎访问尚学堂OA系统</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>
    <%--创建自定义的DIV用来显示提示语--%>
    <div style="text-align: center;">
        <span id="messageSpan" style="color: red;">${requestScope.flag}</span>
    </div>

    <div class="loginbox loginbox2">

        <form action="userServlet" method="post">
            <ul>
                <li><input name="empid" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/></li>
                <li><input name="password" type="password" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
                <li class="yzm">


                    <span><input id="userCode" name="" type="text" value="" /></span>
                    <%--加载验证码信息--%>
                    <cite>
                        <img id="codeImg" style="cursor:pointer;"  src="codeServlet" alt="" width="115px" height="46px">

                    </cite>
                </li>
                <li><input id="submitBtn" type="submit" disabled="disabled" class="loginbtn" value="登录"   /><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
            </ul>
        </form>
    </div>
</div>
</body>

</html>

