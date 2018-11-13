/**
 * 获取所有的区域分类
 */
function getAllRegion() {
    $.ajax({
        url: "/sell/user/region",
        type: "get",
        dataType: "json",
        cache: true,
        success: function (data) {
            var region = data.data;
            var regionTemplet = '<option value="0" selected>请选择区域</option>';
            for (var i = 0; i < region.length; i++) {
                regionTemplet += '<option value="' + region[i].regionId + '">' + region[i].regionName + '</option>'
            }
            $("#regionId").html(regionTemplet)
        }
    })
}

/**
 * 获取商铺的公共Ajax
 * @param url
 */
function getShopCommon(url) {
    $.ajax({
        url: url,
        type: "get",
        dataType: "json",
        cache: true,
        success: function (data) {
            var shop = data.data;
            var shopTemple = '';
            for (var i = 0; i < shop.length; i++) {
                var descr = shop[i].shopDescribe;
                if (descr == null) {
                    descr = "";
                }
                shopTemple += '<div class="col-sm-6 col-md-4 col-lg-3" id="shop">\n' +
                    '                        <a href="/sell/user/shop/'+shop[i].shopId+'" class="alert-dark" ' +
                    '                               style="text-decoration: none">' +
                    '                            <div class="media" style="padding: 10px">\n' +
                    '                               <img width="70px" height="70px" class="mr-3 img-thumbnail"\n' +
                    '                                   src="' + shop[i].shopLogo + '"/>\n' +
                    '                               <div class="media-body" title="' + descr + '">\n' +
                    '                                   <h5 class="mt-0">' + shop[i].shopName + '</h5>\n' +
                    '                                   <p style="color: #6c757d;">' + descr + '</p>\n' +
                    '                               </div>\n' +
                    '                           </div>' +
                    '                       </a>\n' +
                    '                    </div>';
            }
            $("#allShop").html(shopTemple)
        },
        error: function (request) {
            var result = JSON.parse(request.responseText);
            $("#allShop").html("");
            toastTemplet("info", result.msg);
        }
    })
}

/**
 * 获取所有商铺
 */
function getAllShop() {
    getShopCommon('/sell/user/all');
}

/**
 * 用区域分类获取商铺
 * @param regionId
 */
function getShopByRegion(regionId) {
    getShopCommon('/sell/user/region/' + regionId);
}

/**
 * 搜索商铺
 * @param shopName
 */
function searchShop(shopName) {
    getShopCommon('/sell/user/search/' + shopName);
}
