<%--
  Created by IntelliJ IDEA.
  User: ingridliu
  Date: 8/7/21
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //http://localhost:8080/EmployeeSalaryMgt_war_exploded/
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>title</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(function(){
            //Top navigation switch
            $(".nav li a").click(function(){
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>
    <%--Declare js code domain--%>
    <script type="text/javascript">
        //Declare the page load event and add a click event to the exit button
        $(function () {
            $("#loginOut").click(function () {
               var flag= window.confirm("confirm to exit?");
               if(flag){
                   window.location.href="loginOutServlet";
               }
            })
        })
        
        
    </script>

</head>

<body style="background:url(images/topbg.gif) repeat-x;">

<div class="topleft">
    <a href="main.html" target="_parent"><img src="images/logo.png" title="Home" /></a>
</div>

<ul class="nav">
    <li><a href="deptAdd.html" target="rightFrame" class="selected"><img src="images/icon01.png" title="deptAdd" /><h2>Department</h2></a></li>
    <li><a href="empAdd.html" target="rightFrame"><img src="images/icon02.png" title="empAdd" /><h2>Employee</h2></a></li>
    <li><a href="empList.html"  target="rightFrame"><img src="images/icon03.png" title="empList" /><h2>HR</h2></a></li>
    <li><a href="expenseAdd.html"  target="rightFrame"><img src="images/icon04.png" title="reimburseAdd" /><h2>Reimburse</h2></a></li>
    <li><a href="dutyAdd.html" target="rightFrame"><img src="images/icon05.png" title="dutyAdd" /><h2>Salary</h2></a></li>
    <li><a href="myInfo.html"  target="rightFrame"><img src="images/icon06.png" title="myInfo" /><h2>Setting</h2></a></li>
</ul>

<div class="topright">
    <ul>
        <li><span><img src="images/help.png" title="help"  class="helpimg"/></span><a href="tech.html" target="rightFrame">Help</a></li>
        <li><a id="loginOut" href="javascript:void(0)" target="_parent">Exit</a></li>
    </ul>

    <div class="user">
        <%-- Use EL to get the logged-in user name and display it --%>
        <span><a href="myInfo.html" target="rightFrame">${sessionScope.emp.realname}</a></span>
    </div>

</div>

</body>
</html>

