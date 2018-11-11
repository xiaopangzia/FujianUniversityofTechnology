<%--
  Created by IntelliJ IDEA.
  User: Cheng
  Date: 2018/4/2
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
    <link href="<c:url value="/jsps/css/user/login.css"/>" rel="stylesheet" type="text/css">
</head>

<body>
    <div class="divMain">
        <div id="divLogo"><img src="<c:url value="/images/Logo.png"/> "/></div>
        <div>
            <div class="divLoginImage"><img src="<c:url value="/images/loginleft.png"/>"/></div>
            <div class="divLogin">
                <span class="spanTitle">会 员 登 陆</span>
                <span class="spanRegist"><a class="a" href="<c:url value="/jsps/user/regist.jsp"/>">立即注册</a></span>
                <form method="post" action="/UserServlet" id="formLogin">
                    <input type="hidden" name="method" value="login" />
                    <table>
                        <tr>
                            <td><label id="msg">${msg}</label></td>
                            <td height="20">&nbsp;</td>
                        </tr>
                        <tr>
                            <td><input class="inputClass" placeholder="用户名" type="text" name="loginname" id="loginname"/></td>
                        </tr>
                        <tr>
                            <td><label id="loginnameError" class="error"></label></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><input value="${user.loginpass}" class="inputClass" placeholder="密码" type="password" name="loginpass" id="loginpass"/></td>
                        </tr>
                        <tr>
                            <td><label id="loginpassError" class="error"></label></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>
                                <input class="inputClass" type="text" id="verifyCode" name="verifyCode" placeholder="验证码"/>
                                <img  id="imgVerifyCode" src="<c:url value="/VerifyCodeServlet"/>"/>
                                <a href="javascript:_changeImage()">换一张</a>
                            </td>
                        </tr>
                        <tr>
                            <td><label id="verifyCodeError" class="error">${error.verifyCode}</label></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td align="left">
                                <input type="image" src="<c:url value='/images/login1.jpg'/>" id="submitBtn"/>
                                <a class="a" href="<c:url value="/jsps/user/forgotPwd.jsp"/>">忘记密码</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value="/jquery/jquery-3.3.1.js"/> "></script>
    <script type="text/javascript" src="<c:url value="/jsps/js/user/login.js"/>"></script>
    <script type="text/javascript">
        $(function() {/*Map<String(Cookie名称),Cookie(Cookie本身)>*/
            // 获取cookie中的用户名
            var loginname = window.decodeURI("${cookie.loginname.value}");
            if("${requestScope.user.loginname}") {
                loginname = "${requestScope.user.loginname}";
            }
            $("#loginname").val(loginname);
        })
    </script>
</body>
</html>

