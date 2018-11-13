/**
 * 显示商铺商品
 * @param shopId
 * &quot; 解决js添加函数引号的问题
 */
function showCategory(shopId) {

    $.ajax({
        url: "/sell/user/shopMsg/" + shopId,
        dataType: "json",
        type: "get",
        cache: true,
        success: function (data) {
            var product = data.data;
            var categoryShow = '';
            var productShow = '';
            for (let i = 0; i < product.length; i++) {
                categoryShow += '<div class="col-xl-2 col-4 category">\n' +
                    '    <a href="#at' + product[i].categoryType + '" class="text-info">'
                    + product[i].categoryName + '</a></div>';
                productShow += '<div class="col-md-12 border" style="background-color: white;margin-bottom: 30px">\n' +
                    '<a id="at' + product[i].categoryType + '" style="position: absolute; top: -70px;"></a>' +
                    '                        <div class="card-body">\n' +
                    '                        <!--商品分类-->\n' +
                    '                        <h5># ' + product[i].categoryName + '</h5>\n' +
                    '                        <div class="row" id="pd' + product[i].categoryType + '">\n' +
                    '                            \n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>';
            }
            $("#category").html(categoryShow);
            $("#productAll").html(productShow);

            for (let i = 0; i < product.length; i++) {
                var oneProduct = '';
                for (let j = 0; j < product[i].productVOList.length; j++) {
                    oneProduct += ' <div class="col-md-3 col-6 border">\n' +
                        '                                <img src="' + product[i].productVOList[j].productImg + '"\n' +
                        '                                     class="w-100 h-75" style="padding-top: 20px;"/>\n' +
                        '                                <div style="margin: 10px 0">\n' +
                        '                                    <b id="name' + product[i].productVOList[j].productId + '">' + product[i].productVOList[j].productName + '</b>\n' +
                        '                                    <p>\n' +
                        '                                        <b id="price' + product[i].productVOList[j].productId + '" class="float-left">' + product[i].productVOList[j].productPrice + '</b> 元\n' +
                        '                                        <a class="float-right" id="addCart' + product[i].productVOList[j].productId + '" onclick="addCart(&quot;' + product[i].productVOList[j].productId + '&quot;)" ' +
                        '                                          href="javascript:void(0)">\n' +
                        '                                            <span id="number' + product[i].productVOList[j].productId + '"></span>\n' +
                        '                                            <img src="/sell/image/jia.png">\n' +
                        '                                        </a>\n' +
                        '                                    </p>\n' +
                        '                                </div>\n' +
                        '                            </div>';
                }
                $("#pd" + product[i].categoryType).html(oneProduct)
            }
        },
        error: function (request) {
            var result = JSON.parse(request.responseText);
            toastTemplet("warning", result.msg);
        }
    })

}

/**
 * 添加购物车
 * @param productId
 */
function addCart(productId) {
    $("#cart").prop("src", "/sell/image/cartafter.png");
    var name = $("#name" + productId).text();
    var price = $("#price" + productId).text();
    var tbName = $("#tbName" + productId).text();
    var amount = $("#amount").text();
    $("#amount").text((Number(amount)) + (Number(price)));
    $("#number" + productId).prop("class", "number");
    if (tbName != name) {
        var tableTemplet = '';
        tableTemplet += '<tr id="' + productId + '">\n' +
            '                            <td id="tbName' + productId + '">' + name + '</td>\n' +
            '                            <td><a onclick="lessCart(this,&quot;' + productId + '&quot;)"><img src="/sell/image/jian.png"></a>' +
            '                               <span id="tbNum' + productId + '">1</span>' +
            '                               <a onclick="addCart(&quot;' + productId + '&quot;)"><img src="/sell/image/jia.png"></a></td>\n' +
            '                            <td id="tbPrice' + productId + '">' + price + '</td>\n' +
            '                        </tr>';
        $("#tableBody").append(tableTemplet);
        $("#number" + productId).text(1);
    } else {
        $("#tbNum" + productId).text(Number($("#tbNum" + productId).text()) + 1);
        $("#tbPrice" + productId).text(Number($("#tbPrice" + productId).text()) + Number(price));
        $("#number" + productId).text(Number($("#tbNum" + productId).text()));
    }
    $("#shopping").show();
    $("#reset").show();
}

/**
 * 清空购物车
 */
function removeCart() {
    // 清空table里的数据
    $("#tableBody").html('');
    $("#amount").html('');

    // 更换购物车图片
    $("#cart").prop("src", "/sell/image/cart.png");

    // 清除商品的角标
    $(".number").text('');
    $(".number").prop("class", null);

    $("#shopping").hide();
    $("#reset").hide();
}

/**
 * 减少商品数量
 * @param obj
 * @param productId
 */
function lessCart(obj, productId) {
    // 商品数量
    var num = $("#tbNum" + productId).text();

    // 该商品总价
    var price = $("#tbPrice" + productId).text();

    // 商品单价
    var onePrice = Number(price) / Number(num);

    // 购物车总价
    var amount = $("#amount").text();
    if (num == 1) {

        // 重新计算总价
        $("#amount").text(Number(amount) - Number(price));

        // 清除商品角标
        $("#number" + productId).text('');
        $("#number" + productId).prop("class", null);

        // 去除该行数据
        $(obj).parent().parent().remove();
    } else {
        // 重新计算总价
        $("#amount").text(Number(amount) - onePrice);

        // 重新计算购物车该商品总价
        $("#tbPrice" + productId).text(Number(price) - onePrice);

        // 重新计算商品角标
        $("#number" + productId).text(Number(num) - 1);

        // 重新计算购物车数量
        $("#tbNum" + productId).text(Number(num) - 1);
    }

    if ($("#tableBody").find('tr').length == 0) {
        removeCart();
    }
}

/**
 * 下单
 * @param userId
 * @param shopId
 */
function shopping(userId,shopId) {

    if (userId == 'null') {
        window.location.href = "/sell/user/login?url=/sell/user/shop/"+shopId;
        return false;
    }

    var userOrderDetailFormList = [];

    $("#tableBody tr").each(function () {
        var list = {
            "productId": $(this).attr("id"),
            "quantity": $("#tbNum" + $(this).attr("id")).text(),
        }
        userOrderDetailFormList.push(list);
    })

    $.ajax({
        url: "/sell/user/order/create/"+shopId,
        type: "post",
        data: JSON.stringify(userOrderDetailFormList),
        dataType: "json",
        cache: false,
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            var orderId = data.data;
            window.location.href = "/sell/user/order/detail/" + orderId;
        },
        error: function (request) {
            var result = JSON.parse(request.responseText);
            toastTemplet("warning", result.msg);
        }
    });
}
