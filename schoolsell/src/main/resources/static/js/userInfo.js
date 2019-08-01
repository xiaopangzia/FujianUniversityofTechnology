var uid = $("#ur").text();
/**
 * 用户退出登录
 * @param id
 */
function userLogout(id) {
    $.ajax({
        url: "/sell/user/logout/" + id,
        dataType: "json",
        type: "get",
        cache: false,
        success: function () {
            window.location.href = "/sell/user/index";
        },
    })
}

/**
 * 修改用户信息公共
 * @param formId
 * @param url
 */
function updateCommon(url, formId) {
    let bool = true;
    $.ajax({
        url: url,
        data: $(formId).serialize(),
        dataType: "json",
        type: "post",
        cache: false,
        async: false,
        success: function (data) {
            toastTemplet("success", data.msg);
        },
        error: function (request) {
            var result = JSON.parse(request.responseText);
            toastTemplet("warning", result.msg);
            bool = false;
        },
    });
    $(formId)[0].reset();
    return bool;
}

/**
 * 改手机号
 * @param captchaObj
 */
var handler = function (captchaObj) {
    captchaObj.onReady(function () {
        $("#wait").hide();
    }).onSuccess(function () {
        var result = captchaObj.getValidate();
        if (!result) {
            return alert('请完成验证');
        }
        $.ajax({
            url: 'https://www.geetest.com/demo/gt/validate-slide',
            type: 'POST',
            dataType: 'json',
            data: {
                geetest_challenge: result.geetest_challenge,
                geetest_validate: result.geetest_validate,
                geetest_seccode: result.geetest_seccode
            },
            success: function (data) {
                if (data.status === 'success') {
                    updatePhone(uid);
                } else if (data.status === 'fail') {
                    toastTemplet("warning","验证错误")
                    captchaObj.reset();
                }
            }
        });
    });

    $("#unPhone").click(function () {
        if ($("#phone").val().length < 11) {
            toastTemplet("warning", "手机号不能少于11位");
            return false;
        }
        captchaObj.verify();
    })

    // 更多接口说明请参见：http://docs.geetest.com/install/client/web-front/
};
$.ajax({
    // 加随机数防止缓存
    url: "https://www.geetest.com/demo/gt/register-slide?t=" + (new Date()).getTime(),
    type: "get",
    dataType: "json",
    success: function (data) {

        // 调用 initGeetest 进行初始化
        // 参数1：配置参数
        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
        initGeetest({
            // 以下 4 个配置参数为必须，不能缺少
            gt: data.gt,
            challenge: data.challenge,
            offline: !data.success, // 表示用户后台检测极验服务器是否宕机
            new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机

            product: "bind", // 产品形式，包括：float，popup
            width: "300px"

            // 更多配置参数说明请参见：http://docs.geetest.com/install/client/web-front/
        }, handler);
    }
});

/**
 * 改密码
 * @param captchaObj
 */
var handler1 = function (captchaObj) {
    captchaObj.onReady(function () {
        $("#wait").hide();
    }).onSuccess(function () {
        var result = captchaObj.getValidate();
        if (!result) {
            return alert('请完成验证');
        }
        $.ajax({
            url: 'https://www.geetest.com/demo/gt/validate-slide',
            type: 'POST',
            dataType: 'json',
            data: {
                geetest_challenge: result.geetest_challenge,
                geetest_validate: result.geetest_validate,
                geetest_seccode: result.geetest_seccode
            },
            success: function (data) {
                if (data.status === 'success') {
                    updatePwd(uid);
                } else if (data.status === 'fail') {
                    toastTemplet("warning","验证错误")
                    captchaObj.reset();
                }
            }
        });
    });

    $("#unPwd").click(function () {
        var pwd = $("#password").val();
        var newPwd = $("#newPassword").val();
        var verifyNewPwd = $("#verifyNewPassword").val();
        if (pwd.length < 6 && newPwd.length < 6 && verifyNewPwd.length < 6) {
            toastTemplet("warning", "密码至少需要6位");
            return false;
        }
        if (pwd == newPwd) {
            toastTemplet("warning", "原密码与新密码不能相同");
            return false;
        }
        if (newPwd != verifyNewPwd) {
            toastTemplet("warning", "新密码与确认密码需要相同");
            return false;
        }
        captchaObj.verify();
    });


    // 更多接口说明请参见：http://docs.geetest.com/install/client/web-front/
};
$.ajax({
    // 加随机数防止缓存
    url: "https://www.geetest.com/demo/gt/register-slide?t=" + (new Date()).getTime(),
    type: "get",
    dataType: "json",
    success: function (data) {

        // 调用 initGeetest 进行初始化
        // 参数1：配置参数
        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
        initGeetest({
            // 以下 4 个配置参数为必须，不能缺少
            gt: data.gt,
            challenge: data.challenge,
            offline: !data.success, // 表示用户后台检测极验服务器是否宕机
            new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机

            product: "bind", // 产品形式，包括：float，popup
            width: "300px"

            // 更多配置参数说明请参见：http://docs.geetest.com/install/client/web-front/
        }, handler1);
    }
});


/**
 * 修改手机
 * @param userId
 */
function updatePhone(userId) {
    let bool = updateCommon("/sell/user/phone/" + userId, "#phoneForm");
    if (bool) {
        setTimeout(userLogout(userId), 3000);
    }
}

/**
 * 修改用户名
 * @param userId
 */
function updateName(userId) {

    let bool = updateCommon("/sell/user/name/" + userId, "#nameForm");
    if (bool) {
        $("#nameText").text('用户名： ' + name)
    }
}

/**
 * 修改头像
 * @param userId
 */
function updateLogo(userId) {
    const showImg = $("#showImg")[0].src;
    if (showImg == "" || showImg == null) {
        toastTemplet("warning", "请添加图片");
        return false;
    }
    let bool = true;
    $.ajax({
        url: "/sell/user/logo/" + userId,
        data: new FormData($("#logoForm")[0]),
        dataType: "json",
        type: "post",
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            toastTemplet("success", data.msg);
            $("#imgLogo").prop("src", showImg);
        },
        error: function (request) {
            var result = JSON.parse(request.responseText);
            toastTemplet("warning", result.msg);
            $("#logoForm")[0].reset();
        },
    });
}

/**
 * 修改密码
 * @param userId
 */
function updatePwd(userId) {
    let bool = updateCommon("/sell/user/pwd/" + userId, "#pwdForm");
    if (bool) {
        setTimeout(userLogout(userId), 3000);
    }
}

/**
 * 修改地址
 * @param userId
 */
function updateAddress(userId) {
    const address = $("#address").val();
    if (address === "" || address === null) {
        toastTemplet("warning", "地址不能为空");
        return false;
    }
    let bool = updateCommon("/sell/user/address/" + userId, "#addressForm");
    if (bool) {
        $("#addressText").text('地址： ' + address);
    }
}
