<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>未来24小时天气</title>
<!-- 引入 ECharts 文件 -->
<script src="js/echarts.min.js"></script>
</head>
<body>
	请选择城市：
	<select onchange="selectCity();" name="cityid" id="cityid">
		<option></option>
		<c:forEach items="${cityList}" var="city">
			<c:if test="${city.cityid == cityid}">
				<option value="${city.cityid }" selected="selected">${city.city }</option>
			</c:if>
			<c:if test="${city.cityid != cityid}">
				<option value="${city.cityid }">${city.city }</option>
			</c:if>
		</c:forEach>
	</select>

	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="main" style="height: 600px;"></div>

	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '未来24小时气温变化'
			},
			tooltip : {},
			legend : {
				data : [ '温度' ]
			},
			xAxis : {
				name : '单位/小时',
				data : <c:out value="${times}" escapeXml="false"></c:out>
			},
			yAxis : {
				name : '气温'
			},
			series : [ {
				name : '温度',
				type : 'line',
				data : <c:out value="${temps}" escapeXml="false"></c:out>
			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>
	<script type="text/javascript">
		function selectCity() {
			//当前下来列表选中的值
			var cityid = document.getElementById('cityid').value;
			//alert(cityid);
			//跳转到/index  servlet
			window.location.href = "/weather?cityid=" + cityid;
		}
	</script>
</body>
</html>