$(function () {

    /**
     * 1.得到焦点时,隐藏错误信息
     */
    $(".inputClass").focus(function () {
        var inputName=$(this).attr("name");
        $("#"+inputName+"Error").text("");
    });

    /**
     * 2.失去焦点时，显示错误信息
     */
    $(".inputClass").blur(function () {
        var inputName=$(this).attr("name");
        validateReplaceName(inputName);
    });

    /*
     * 3. 表单提交时验证
     */
    $("#ChangePwd").submit(function () {
        var bool=true;
        if (!validateLoginpass()) {
            bool = false;
        }
        if (!validateNewloginpass()) {
            bool = false;
        }
        if (!validateReloginpass()) {
            bool = false;
        }
        if (!validateVerifyCode()) {
            bool = false;
        }
        return bool;
    });

});

/**
 * 查找对应函数，替换第二个单词首位为大写
 * substring（）定位
 * toUpperCase()换成大写
 * @param inputName
 * @returns {any}
 */
function validateReplaceName(inputName) {
    inputName=inputName.substring(0,1).toUpperCase()+inputName.substring(1);
    var functionName="validate"+inputName;
    return eval(functionName+"()");
}

/**
 * 本地校验旧密码
 */
function validateLoginpass() {
    var bool=true;
    var name=$("#loginpass");
    var error=$("#loginpassError");
    error.css("display","none");
    if(!name.val()){//非空校验
        error.css("display","");
        error.text("旧密码不能为空！");
        bool=false;
    }
    if((name.val().length<3&&name.val().length!=0)||name.val().length>20){
        //长度校验
        error.css("display","");
        error.text("旧密码长度必须在3到20之间！");
        bool=false;
    }
    return bool;
}

/**
 * 本地校验新密码
 */
function validateNewloginpass() {
    var bool=true;
    var name=$("#newloginpass");
    var error=$("#newloginpassError");
    error.css("display","none");
    if(!name.val()){//非空校验
        error.css("display","");
        error.text("新密码不能为空！");
        bool=false;
    }
    if((name.val().length<3&&name.val().length!=0)||name.val().length>20){
        //长度校验
        error.css("display","");
        error.text("新密码长度必须在3到20之间！");
        bool=false;
    }
    return bool;
}

/**
 * 本地校验确认密码
 */
function validateReloginpass() {
    var bool=true;
    var name=$("#reloginpass");
    var error=$("#reloginpassError");
    error.css("display","none");
    if(!name.val()){//非空校验
        error.css("display","");
        error.text("确认密码不能为空！");
        bool=false;
    }
    if((name.val().length<3&&name.val().length!=0)||name.val().length>20){
        //长度校验
        error.css("display","");
        error.text("确认密码长度必须在3到20之间！");
        bool=false;
    }
    return bool;
}

/**
 * 本地校验验证码
 */
function validateVerifyCode() {
    var bool=true;
    var name=$("#verifyCode").val();
    var error=$("#verifyCodeError");
    error.css("display","none");
    if(!name){//非空校验
        error.css("display","");
        error.text("验证码不能为空！");
        bool=false;
    }else if(name.length!=4&&name.length!=0){
        //长度校验
        error.css("display","");
        error.text("验证码长度必须为4位！");
        bool=false;
    }
    else{
        $.ajax({
            url:"/UserServlet",
            data:{method:"validateVerifyCode",verifyCode:name},
            dataType:"json",
            async:false,
            cache:false,
            type:"POST",
            success:function(result){
                if(!result){
                    error.css("display","");
                    error.text("验证码错误！");
                    bool=false;
                }
            }
        });
    }
    return bool;
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