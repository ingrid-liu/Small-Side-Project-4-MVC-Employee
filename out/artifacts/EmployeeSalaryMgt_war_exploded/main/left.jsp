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
            //导航切换
            $(".menuson .header").click(function(){
                var $parent = $(this).parent();
                $(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();

                $parent.addClass("active");
                if(!!$(this).next('.sub-menus').size()){
                    if($parent.hasClass("open")){
                        $parent.removeClass("open").find('.sub-menus').hide();
                    }else{
                        $parent.addClass("open").find('.sub-menus').show();
                    }


                }
            });

            // 三级菜单点击
            $('.sub-menus li').click(function(e) {
                $(".sub-menus li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function(){
                var $ul = $(this).next('ul');
                $('dd').find('.menuson').slideUp();
                if($ul.is(':visible')){
                    $(this).next('.menuson').slideUp();
                }else{
                    $(this).next('.menuson').slideDown();
                }
            });
        })
    </script>

</head>

<body style="background:#f0f9fd;">
<div class="lefttop"><span></span>Navigation</div>

<dl class="leftmenu">
    <dd>
        <div class="title"><span><img src="images/leftico03.png" /></span>Employee</div>
        <ul class="menuson">
            <li><cite></cite><a href="deptAdd.html" target="rightFrame">Add new Department</a><i></i></li>
            <li><cite></cite><a href="deptList.html" target="rightFrame">Department management</a><i></i></li>
            <li><cite></cite><a href="positionAdd.html" target="rightFrame">Add new position</a><i></i></li>
            <li><cite></cite><a href="positionList.html" target="rightFrame">Position management</a><i></i></li>
            <li><cite></cite><a href="empAdd.html" target="rightFrame">Add new Employee</a><i></i></li>
            <li><cite></cite><a href="empList.html" target="rightFrame">Employee management</a><i></i></li>
        </ul>
    </dd>
    <dd>
        <div class="title"><span><img src="images/leftico03.png" /></span>Attendance</div>
        <ul class="menuson">
            <li><cite></cite><a href="dutyAdd.html" target="rightFrame">Add new time sheet</a><i></i></li>
            <li><cite></cite><a href="dutyList.html" target="rightFrame">Attendance management</a><i></i></li>
            <li><cite></cite><a href="myDuty.html" target="rightFrame">My attendance</a><i></i></li>
        </ul>
    </dd>
    <dd>
        <div class="title"><span><img src="images/leftico03.png" /></span>Reimbursement</div>
        <ul class="menuson">
            <li><cite></cite><a href="expenseAdd.html" target="rightFrame">New reimbursement</a><i></i></li>
            <li><cite></cite><a href="toAudit.html" target="rightFrame">Pending reimbursement</a><i></i></li>
            <li><cite></cite><a href="myExpense.html" target="rightFrame">My reimbursement</a><i></i></li>
            <li><cite></cite><a href="myAudit.html" target="rightFrame">Reimburse history</a><i></i></li>
        </ul>
    </dd>
    <dd>
        <div class="title"><span><img src="images/leftico03.png" /></span>Salary</div>
        <ul class="menuson">
            <li><cite></cite><a href="incomeAdd.html" target="rightFrame">Add salary item</a><i></i></li>
            <li><cite></cite><a href="incomeList.html" target="rightFrame">Check salary status</a><i></i></li>
            <li><cite></cite><a href="incomestatis.html" target="rightFrame">Income statistics</a><i></i></li>
            <li><cite></cite><a href="expenseList.html" target="rightFrame">Expenditure</a><i></i></li>
            <li><cite></cite><a href="expensestatis.html" target="rightFrame">Expenditure statistics</a><i></i></li>
        </ul>
    </dd>



    <dd>
        <div class="title"><span><img src="images/leftico03.png" /></span>Setting</div>
        <ul class="menuson">
            <li><cite></cite><a href="myInfo.html" target="rightFrame">Personal info</a><i></i></li>
            <li><cite></cite><a href="myPwd.html" target="rightFrame">Change password</a><i></i></li>
        </ul>
    </dd>
</dl>

</body>

</html>
