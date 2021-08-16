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
    <script type="text/javascript" src="js/jquery.js"></script>

</head>


<body>

<div class="place">
    <span>Payroll Sys：</span>
    <ul class="placeul">
        <li><a href="#">HOME</a></li>
    </ul>
</div>

<div class="mainindex">


    <div class="welinfo">
        <span><img src="images/sun.png" alt="Weather" /></span>
        <b>Hi! Welcome to login Ingrid & Ryan's employee management system</b>

    </div>




</div>



</body>

</html>
