<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

    <#include "../common/nav.ftl">

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTOPage.content as orderDTO>
                <tr>
                    <td>${orderDTO.orderId}</td>
                    <td>${orderDTO.buyerName}</td>
                    <td>${orderDTO.buyerPhone}</td>
                    <td>${orderDTO.buyerAddress}</td>
                    <td>${orderDTO.orderAmount}</td>
                    <td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
                    <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                    <td>${orderDTO.createTime}</td>
                    <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                    <td>
                        <#if orderDTO.getOrderStatusEnum().message == "新订单">
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#--lte 小于等于-->
                <#if currentPage lte 1>
                <li class="disabled"><a href="">上一页</a></li>
                <#else>
                <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                </#if>
                    <#-- 0.. 0到多少 -->
                <#list 1..orderDTOPage.getTotalPages() as index>
                    <#if currentPage == index>
                <li class="disabled"><a href="">${index}</a></li>
                    <#else>
                    <li>
                        <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                    </li>
                    </#if>
                </#list>
                    <#--gte 大于等于-->
                <#if currentPage gte orderDTOPage.getTotalPages()>
                <li class="disabled"><a href="">下一页</a></li>
                <#else>
                <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新订单
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新订单</button>
            </div>
        </div>

    </div>

</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://127.0.0.1:8080/sell/webSocket");
    }else {
        alert('不支持WebSocket');
    }

    webSocket.onopen = function (ev) {
        console.log('建立连接');
    };

    webSocket.onclose = function (ev) {
        console.log('连接关闭')
    }

    webSocket.onmessage = function (ev) {
        console.log('接收消息' + ev.data);
        $('#myModal').modal('show');
    }

    webSocket.onerror = function (ev) {
        console.log('通信错误')
    }

    webSocket.onbeforeunload = function () {
        webSocket.close();

    };
</script>
</html>
