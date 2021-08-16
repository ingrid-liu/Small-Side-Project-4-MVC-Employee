<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'incomestatics.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/echarts.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$.ajax({
				url:"servlet/InOutServlet?method=getBarData",
				success:function(data){
					//json string----json object 
					eval("var arr = "+data);//[['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],[120, 200, 1500, 800, 70, 110, 130]]
					
					//
					var dom = document.getElementById("container");
					var myChart = echarts.init(dom);
					//
					var option = {
					    title: {
					                text: '尚学堂收入统计图表'
					            },
					    xAxis: {
					        type: 'category',
					        data: arr[0]
					    },
					    yAxis: {
					        type: 'value'
					    },
					    series: [{
					        data: arr[1],
					        type: 'bar'
					    }]
					};
					//
					if (option && typeof option === "object") {
					    myChart.setOption(option, true);
					}
						
				}
			});			
			
		});
	
	</script>
  </head>
  
  <body>
     <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="container" style="height: 100%"></div>
  </body>
</html>
