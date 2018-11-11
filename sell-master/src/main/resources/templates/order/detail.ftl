<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">
<div id="page-content-wrapper">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <h3>订单详情</h3>
                        <p>订单ID: ${orderDTO.orderId} 创建时间:${orderDTO.createTime}</p>
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>商品id</th>
                                <th>商品名称</th>
                                <th>价格</th>
                                <th>数量</th>
                                <th>总额</th>
                            </tr>
                            </thead>
                            <tbody>
                        <#list orderDTO.orderDetailList as detail>
                        <tr>
                            <td>${detail.productId}</td>
                            <td>${detail.productName}</td>
                            <td>${detail.productPrice}</td>
                            <td>${detail.productQuantity}</td>
                            <td>${detail.productPrice * detail.productQuantity}</td>
                        </tr>
                        </#list>
                            <tr>
                                <td style="text-align: right" colspan="5">订单总金额: ${orderDTO.orderAmount} 元</td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="col-md-12 column">
                        <#if orderDTO.getOrderStatusEnum().message == "新订单">
                            <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button"
                               class="btn btn-lg btn-info">完结订单</a>
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button"
                               class="btn btn-lg btn-danger">取消订单</a>
                        </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
</body>
</html>
