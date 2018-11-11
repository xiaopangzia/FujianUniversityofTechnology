$(function () {

    /*
     * 1.得到所有的错误信息，循环遍历。调用一个方法是否显示错误信息
     */
    $(".errorClass").each(function () {
        showError($(this));
    });

    /*
     * 2. 切换注册按钮图片
     */
    $("#submitBtn").hover(
        function () {
            $("#submitBtn").attr("src", "/images/regist2.jpg");
        },
        function () {
            $("#submitBtn").attr("src", "/images/regist1.jpg");
        }
    );

    /*
     * 3. 输入框得到焦点隐藏错误信息
     */
    $(".inputClass").focus(function () {
        var labelId = $(this).attr("id") + "Error";//通过输入框找到对应的label的id
        $("#" + labelId).text("");//把label的内容清空
        showError($("#" + labelId));//隐藏没有信息的label
    });

    /*
     * 4. 输入框失去焦点进行校验
     */
    $(".inputClass").blur(function () {
        var id = $(this).attr("id");
        //取第一个值换成大写                 取从第一个开始后面的值
        var funName = "validate" + id.substring(0, 1).toUpperCase() + id.substring(1) + "()";//得到对应函数名
        eval(funName);//执行函数调用
    });

    /*
     * 5. 表单提交时验证
     */
    $("#registForm").submit(function () {
        var bool=true;
        if (!validateLoginname()) {
            bool = false;
        }
        if (!validateLoginpass()) {
            bool = false;
        }
        if (!validateReloginpass()) {
            bool = false;
        }
        if (!validateEmail()) {
            validateEmail();
        }
        if (!validateVerifyCode()) {
            validateEmail();
        }
        return bool;
    });
});

/*
 *登录名校验
 */
function validateLoginname() {
    var id = "loginname";
    var value = $("#" + id).val();//输入框内容

    /*
     * 1. 非空
     */
    if (!value) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        $("#" + id + "Error").text("用户名不能为空！");
        showError($("#" + id + "Error"));
        return false;
    }

    /*
     * 2. 长度
     */
    if ((value.length < 3 && value.length > 0) || value.length > 20) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        $("#" + id + "Error").text("用户名必须在3~20之间！");
        showError($("#" + id + "Error"));
        return false;
    }
    /*
     * 3. 是否注册校验
     */
    $.ajax({
        //要请求的服务器url
        url: "/UserServlet",
        //这是一个对象，他代表请求参数：method=方法名&val=value,服务端可以用getParamter（）来获取
        data: {method: "ajaxValidateLoginname", loginname: value},
        //是否为异步请求
        async: false,
        //是否缓存结果
        cache: false,
        //请求方式
        type: "POST",
        //服务端返回的数据是什么类型
        dataType: "json",
        //这个函数会在服务器执行成功时被调用，参数result就是服务器返回的值
        success: function (result) {

            if (!result) {//如果校验失败
                $("#" + id + "Error").text("用户名已存在！");
                showError($("#" + id + "Error"));
                false;
            }
        }
    });
    return true;

}

/*
 *登录密码校验
 */
function validateLoginpass() {
    var id = "loginpass";
    var value = $("#" + id).val();//输入框内容

    /*
     * 1. 非空
     */
    if (!value) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        $("#" + id + "Error").text("密码不能为空！");
        showError($("#" + id + "Error"));
        return false;
    }

    /*
     * 2. 长度
     */
    if ((value.length < 3 && value.length > 0) || value.length > 20) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        $("#" + id + "Error").text("密码必须在3~20之间！");
        showError($("#" + id + "Error"));
        return false;
    }

    return true;
}

/*
 *确认密码校验
 */
function validateReloginpass() {
    var id = "reloginpass";
    var value = $("#" + id).val();//输入框内容

    /*
     * 1. 非空
     */
    if (!value) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        $("#" + id + "Error").text("确认密码不能为空！");
        showError($("#" + id + "Error"));
        return false;
    }

    /*
     * 2. 两次输入是否一致校验
     */
    if (value != $("#loginpass").val()) {
        /*
        * 获取对应的label
        * 添加错误信息
        * 显示label
        */
        $("#" + id + "Error").text("两次密码不一致！");
        showError($("#" + id + "Error"));
        return false;
    }


    return true;
}

/*
 *Email校验
 */
function validateEmail() {
    var id = "email";
    var value = $("#" + id).val();//输入框内容

    /*
     * 1. 非空
     */
    if (!value) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        $("#" + id + "Error").text("Email不能为空！");
        showError($("#" + id + "Error"));
        return false;
    }

    /*
     * 2. Email格式校验
     * 正则表达式
     */
    if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)) {
        /*
        * 获取对应的label
        * 添加错误信息
        * 显示label
        */
        $("#" + id + "Error").text("错误的Email格式！");
        showError($("#" + id + "Error"));
        return false;
    }

    /*
     * 3. 是否注册校验
     */
    $.ajax({
        //要请求的服务器url
        url: "/UserServlet",
        //这是一个对象，他代表请求参数：method=方法名&val=value,服务端可以用getParamter（）来获取
        data: {method: "ajaxValidateEmail", email: value},
        //是否为异步请求
        async: false,
        //是否缓存结果
        cache: false,
        //请求方式
        type: "POST",
        //服务端返回的数据是什么类型
        dataType: "json",
        //这个函数会在服务器执行成功时被调用，参数result就是服务器返回的值
        success: function (result) {
            if (!result) {//result不为真 如果校验失败
                $("#" + id + "Error").text("Email已存在！");
                showError($("#" + id + "Error"));
                return false;
            }
        }
    });
    return true;
}

/*
 *验证码校验
 */
function validateVerifyCode() {
    var id = "verifyCode";
    var value = $("#" + id).val();//输入框内容

    /*
     * 1. 非空
     */
    if (!value) {
        /*
         * 获取对应的label
         * 添加错误信息
         * 显示label
         */
        $("#" + id + "Error").text("验证码不能为空！");
        showError($("#" + id + "Error"));
        return false;
    }

    /*
     * 2. 长度验证
     */
    if (value.length != 4) {
        /*
        * 获取对应的label
        * 添加错误信息
        * 显示label
        */
        $("#" + id + "Error").text("验证码长度为4位！");
        showError($("#" + id + "Error"));
        return false;
    }

    /*
     * 3. 是否注册校验
     */
    $.ajax({
        url: "/UserServlet",
        data: {method: "validateVerifyCode", verifyCode: value},
        type: "POST",
        cache: false,
        dataType: "json",
        async: false,
        success: function (result) {
            if (!result) {
                $("#verifyCodeError").css("display", "");
                $("#verifyCodeError").text("错误的验证码!");
                return false;
            }
        }
    })
    return true;
}

/*
 *异步换验证码
 */
function _changeImage() {
    /*
     *1.获取image元素
     *2.重新设置他的css
     *3.使用毫秒数添加参数
     */
    $("#imgVerifyCode").attr("src", "/VerifyCodeServlet?a=" + new Date().getTime());
}

/*
 *判断当前元素是否存在内容，如果有就显示，没有就不显示
 */
function showError(ele) {
    var text = ele.text();//获取元素的内容
    if (!text) {
        //如果没有元素就隐藏元素
        ele.css("display", "none");
    } else {
        //有就显示
        ele.css("display", "");
    }
}

