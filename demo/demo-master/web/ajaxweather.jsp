<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>未来24小时天气</title>
    <!-- 引入 ECharts 文件 -->
    <script src="js/echarts.min.js"></script>
    <script src="js/jquery-3.2.1.js"></script>
</head>
<body>
请选择城市：
<select onclick="selectCity();" name="cityid" id="cityid">
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

<script>
    var myChart = echarts.init(document.getElementById('main'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title : {
            text : '未来24小时气温变化'
        },
        tooltip : {},
        legend : {
            data : [ '温度' ]
        },
        xAxis : {
            name : '单位/小时',
            data : []
        },
        yAxis : {
            name : '气温'
        },
        series : [ {
            name : '温度',
            type : 'line',
            data : []
        } ]
    });
</script>

<script type="text/javascript">
    function selectCity() {
        //当前下来列表选中的值
        var cityid = document.getElementById('cityid').value;
        // 异步加载数据
        $.get('/getWeather?cityid=' + cityid, function(data) {
            //alert(data.times);
            //alert(data.temps);
            // 填入数据
            myChart.setOption({
                xAxis : {
                    data : data.times
                },
                series : [ {
                    // 根据名字对应到相应的系列
                    name : '温度',
                    data : data.temps
                } ]
            });
        }, 'json');
    }
</script>
</body>
</html>

