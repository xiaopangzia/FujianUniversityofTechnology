/**
 * 页面展示
 * @param orderId
 */
function showDetail(orderId) {

    $.ajax({
        url: "/sell/user/order/getDetail/" + orderId,
        type: "get",
        dataType: "json",
        cache: false,
        success: function (data) {
            var detailBody = '';
            var result = data.data;
            var status = result.orderStatus;
            if (status == 0) {
                detailBody += '<div class="col-12" style="margin-bottom: 30px">\n' +
                    '                    <div class="card">\n' +
                    '                        <div class="card-header">\n' +
                    '                            <span>状态：未支付</span>\n' +
                    '                            <span class="float-right">\n' +
                    '                                <button class="btn" onclick="cancelPay(&quot;'+orderId+'&quot;)">取消</button>\n' +
                    '                                <button class="btn btn-danger" onclick="toPay(&quot;' + orderId + '&quot;)">付款</button>\n' +
                    '                            </span>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>';
            } else if (status == 1) {
                detailBody += '<div class="col-12" style="margin-bottom: 30px">\n' +
                    '                    <div class="card">\n' +
                    '                        <div class="card-header">\n' +
                    '                            <span>状态：已支付</span>\n' +
                    '                            <span class="float-right">\n' +
                    '                                <button class="btn" onclick="javascript:alert(\'请联系商家取消订单,商家电话(' + result.shopName + ':' + result.shopPhone + ')\')">取消</button>\n' +
                    '                            </span>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>';
            } else {
                detailBody += '<div class="col-12" style="margin-bottom: 30px">\n' +
                    '                    <div class="card">\n' +
                    '                        <div class="card-header">\n' +
                    '                            <span>状态：已取消</span>\n' +
                    '                            <div class="float-right">商家电话：' + result.shopPhone + '</div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>';
            }

            detailBody += '                <div class="col-md-6" style="padding-bottom: 30px">\n' +
                '                    <div class="card">\n' +
                '                        <div class="card-header">\n' +
                '                            <a style="color: black;text-decoration: none" href="/sell/user/shop/'+result.shopId+'" class="media">\n' +
                '                                <img class="mr-3" width="60px" height="60px" src="'+result.shopImg+'"/>\n' +
                '                                <div class="media-body">\n' +
                '                                    <h5 class="mt-0">\n' +
                '                                        ' + result.shopName + '\n' +
                '                                    </h5>订单号：' + result.orderId + '\n' +
                '                                </div>\n' +
                '                            </a>\n' +
                '                        </div>\n' +
                '                        <div class="card-body">\n' +
                '                            <table id="tableShow" class="card-text table">\n' +
                '                                <thead>\n' +
                '                                <tr>\n' +
                '                                    <th>商品名</th>\n' +
                '                                    <th>数量</th>\n' +
                '                                    <th>单价(元)</th>\n' +
                '                                    <th>小计(元)</th>\n' +
                '                                </tr>\n' +
                '                                </thead>\n' +
                '                                <tbody id="tableBody">\n' +
                '                                </tbody>\n' +
                '                            </table>\n' +
                '                        </div>\n' +
                '                        <div class="card-footer">\n' +
                '                            <span class="float-right">总价：' + result.orderAmount + ' 元</span>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                </div>';

            var product = result.orderDetailVOs;

            var tableBody = '';
            for (let i = 0; i < product.length; i++) {
                tableBody += '<tr>\n' +
                    '                                    <td>' + product[i].productName + '</td>\n' +
                    '                                    <td>' + product[i].productQuantity + '</td>\n' +
                    '                                    <td>' + product[i].productPrice + '</td>\n' +
                    '                                    <td>' + product[i].productAmount + '</td>\n' +
                    '                                </tr>';
            }

            if (status == 0) {
                detailBody += ' <div class="col-md-6">\n' +
                    '                    <div class="card">\n' +
                    '                        <div class="card-header">\n' +
                    '                            <h5 class="card-text">配送信息</h5>\n' +
                    '                        </div>\n' +
                    '                        <div class="card-body">\n' +
                    '                            <form role="form" id="editUser" style="display: none">\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label for="username">\n' +
                    '                                        姓名\n' +
                    '                                    </label>\n' +
                    '                                    <input type="text" value="' + result.username + '" class="form-control" id="username" name="username" />\n' +
                    '                                </div>\n' +
                    '\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label for="phone">\n' +
                    '                                        电话\n' +
                    '                                    </label>\n' +
                    '                                    <input type="text" value="' + result.phone + '" class="form-control" id="phone" name="phone" />\n' +
                    '                                </div>\n' +
                    '\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label for="address">\n' +
                    '                                        地址\n' +
                    '                                    </label>\n' +
                    '                                    <input type="text" value="' + result.address + '" class="form-control" id="address" name="address" />\n' +
                    '                                </div>\n' +
                    '\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label for="orderMessage">\n' +
                    '                                        备注\n' +
                    '                                    </label>\n' +
                    '                                    <input type="text" value="' + result.orderMessage + '" class="form-control" id="orderMessage" name="orderMessage" />\n' +
                    '                                </div>\n' +
                    '\n' +
                    '                                <button onclick="saveForm(&quot;' + orderId + '&quot)" type="button" class="btn btn-primary">\n' +
                    '                                    提交\n' +
                    '                                </button>\n' +
                    '                                <button onclick="exitEdit()" type="button" class="btn">\n' +
                    '                                    返回\n' +
                    '                                </button>\n' +
                    '                            </form>\n' +
                    '\n' +
                    '                            <div id="showUser">\n' +
                    '                                <p>姓名:<span id="showName">' + result.username + '</span></p>\n' +
                    '                                <p>电话:<span id="showPhone">' + result.phone + '</span></p>\n' +
                    '                                <p>地址:<span id="showAddress">' + result.address + '</span></p>\n' +
                    '                                <p>备注:<span id="showMessage">' + result.orderMessage + '</span></p>\n' +
                    '                                <button onclick="toEdit()" class="btn btn-primary">修改</button>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>';
            }else {
                detailBody += ' <div class="col-md-6">\n' +
                    '                    <div class="card">\n' +
                    '                        <div class="card-header">\n' +
                    '                            <h5 class="card-text">配送信息</h5>\n' +
                    '                        </div>\n' +
                    '                        <div class="card-body">\n' +
                    '\n' +
                    '                            <div id="showUser">\n' +
                    '                                <p>姓名:<span id="showName">' + result.username + '</span></p>\n' +
                    '                                <p>电话:<span id="showPhone">' + result.phone + '</span></p>\n' +
                    '                                <p>地址:<span id="showAddress">' + result.address + '</span></p>\n' +
                    '                                <p>备注:<span id="showMessage">' + result.orderMessage + '</span></p>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>';
            }



            $("#detailBody").html(detailBody);
            $("#tableBody").html(tableBody);


        },
        error: function (request) {
            var result = JSON.parse(request.responseText);
            toastTemplet("warning", result.msg);
        }
    })
}

/**
 * 点击去修改信息
 */
function toEdit() {
    $("#editUser").show();
    $("#showUser").hide();
}

/**
 * 退出修改
 */
function exitEdit() {
    $("#editUser").hide();
    $("#showUser").show();
}

/**
 * @param orderId
 * 保存修改
 */
function saveForm(orderId) {
    $.ajax({
        url: "/sell/user/order/update/" + orderId,
        type: "post",
        data: $("#editUser").serialize(),
        dataType: "json",
        cache: false,
        success: function (data) {
            $("#showName").text($("#username").val());
            $("#showPhone").text($("#phone").val());
            $("#showAddress").text($("#address").val());
            $("#showMessage").text($("#orderMessage").val());
            $("#editUser").hide();
            $("#showUser").show();
            toastTemplet("success", data.msg);
        },
        error: function (request) {
            var result = JSON.parse(request.responseText);
            toastTemplet("warning", result.msg);
        }
    })
}

/**
 * 支付
 * @param orderId
 */
function toPay(orderId) {
    var code = {"code": 1}
    statusCommon(code);
}

/**
 * 取消订单
 * @param orderId
 */
function cancelPay(orderId) {
    var code = {"code": 2};
    if (confirm("确定取消订单吗")) {
        statusCommon(code);
    }

}

/**
 * 修改状态公共
 * @param code
 */
function statusCommon(code) {
    $.ajax({
        url: "/sell/user/order/status/" + orderId,
        data: code,
        dataType: "json",
        type: "post",
        cache: false,
        success:function (data) {
            toastTemplet("success", data.msg + ",三秒后刷新页面");
            setTimeout(function () {
                window.location.reload();
            }, 3000);
        },
        error:function (request) {
            var result = JSON.parse(request.responseText);
            toastTemplet("warning", result.msg);
        }
    })
}

