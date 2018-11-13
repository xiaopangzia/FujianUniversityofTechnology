/**
 * 用户退出登录
 * @param id
 */
function userLogout(id) {
    $.ajax({
        url: "/sell/user/logout/" + id,
        dataType: "json",
        type:"get",
        cache:false,
        success:function () {
            window.location.href = "/sell/user/index";
        },
    })
}

/**
 * 修改用户信息公共
 * @param formId
 * @param url
 */
function updateCommon(url,formId) {
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
 * 修改手机
 * @param userId
 */
function updatePhone(userId) {
    if ($("#phone").val().length < 9) {
        toastTemplet("warning", "手机号不能少于9位");
        return false;
    }
    let bool = updateCommon("/sell/user/phone/" + userId, "#phoneForm");
    if (bool) {
        setTimeout(userLogout(userId), 1000);
    }
}

/**
 * 修改用户名
 * @param userId
 */
function updateName(userId) {
    const name = $("#username").val();
    if (name == "") {
        toastTemplet("warning", "用户名不能为空");
        return false;
    }
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
    var pwd = $("#password").val();
    var newPwd = $("#newPassword").val();
    var verifyNewPwd = $("#verifyNewPassword").val();
    if (pwd.length < 9 && newPwd.length < 9 && verifyNewPwd.length < 9) {
        toastTemplet("warning", "密码至少需要9位");
        return false;
    }
    if (newPwd != verifyNewPwd) {
        toastTemplet("warning", "新密码与确认密码需要相同");
        return false;
    }
    let bool = updateCommon("/sell/user/pwd/" + userId, "#pwdForm");
    if (bool) {
        setTimeout(userLogout(userId),3000);
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
        $("#addressText").text('地址： '+address);
    }
}
