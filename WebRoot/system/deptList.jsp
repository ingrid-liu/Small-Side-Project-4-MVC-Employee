<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"></base>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>无标题文档</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(){
	  $(".click").click(function(){
	  $(".tip").fadeIn(200);
	  });
	  
	  $(".tiptop a").click(function(){
	  $(".tip").fadeOut(200);
	});
	
	  $(".sure").click(function(){
	  $(".tip").fadeOut(100);
	});
	
	  $(".cancel").click(function(){
	  $(".tip").fadeOut(100);
	});
	
	});
	
	
	function deleteDept(deptno){
		var flag = window.confirm("您确定要删除该部门吗");
		if(flag){
			location.href="servlet/DepartmentServlet?method=delete&deptno="+deptno;
		}
		
	}
	</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">人事管理</a></li>
    <li><a href="#">部门管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
      
   <div class="formtitle1"><span>部门列表</span></div>
   
    <table class="tablelist" >
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>部门名称</th>
        <th>办公地点</th>
        <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        
        <c:forEach items="${deptList }" var="dept">
        	 <tr>
		        <td><input name="" type="checkbox" value="" /></td>
		        <td>${dept.deptno }</td>
		        <td>${dept.deptName }</td>
		        <td>${dept.location }</td>
		        <td><a href="servlet/DepartmentServlet?method=findById&deptno=${dept.deptno  }" class="tablelink">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;  <a href="javascript:deleteDept(${dept.deptno })" class="tablelink"> 删除</a></td>
	        </tr> 
        </c:forEach>
        </tbody>
    </table>
    
       
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" onclick=""/>&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
 