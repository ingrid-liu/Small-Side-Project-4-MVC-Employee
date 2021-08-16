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
		<title>审核报销单</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript">
		$(function(){
			$("#btn1").click(function(){
				//获取表单项的值
				var expId = $("#expId").val();
				var result = "";
				var resultArr = $("input[name=result]");
				for(var i=0;i<resultArr.length;i++){
					var flag = $(resultArr[i]).attr("checked");//1.4使用attr，1.6之后使用prop
					//var flag = resultArr[i].checked;
					if(flag){
						result = $(resultArr[i]).val();
						//result = resultArr[i].value;
						break;
					}
				}
				var auditDesc = $("#auditDesc").val();
				//通过Ajax提交表单，根据结果调用回调函数
				alert(expId+"  "+result+"  "+auditDesc);
				$.ajax({
					url:"servlet/ExpenseServlet?method=audit",
					type:"POST",
					data:{expId:expId,result:result,auditDesc:auditDesc},
					dataType:"text",
					success:function(data){
						//alert(data);
						if(data=="success"){
							 //刷新父窗口
							 //window.location.reload();刷新当前窗口
							 window.opener.location.reload();
							 //关闭当前窗口
							 window.close();
						}else{
							alert("审核失败");
						}
					}				
				});
			
			});
		});
			
		</script>
	</head>

	<body>

		<div class="formtitle"><span>审核报销单</span></div>
		<form action="#">
			<ul class="forminfo">
				<li>
					<label>报销单编号</label>
					<input name="" id="expId" type="text" class="dfinput" value="${param.expId }" readonly="readonly"/>
				</li>
				<li>
					<label>审核结果</label>
					<input name="result" type="radio" value="通过" />通过
					<input  name="result" type="radio" value="拒绝" />拒绝 
					<input name="result" type="radio" value="打回"/>打回 
				</li>
				<li>
					<label>审核意见</label>
					<input id="auditDesc" name="" type="text" class="dfinput" />
				</li>
				<li>
					<label>&nbsp;</label>
					<input name="" id="btn1" type="button" class="btn" value="确认保存" />
				</li>
			</ul>
		</form>
	</body>

</html>