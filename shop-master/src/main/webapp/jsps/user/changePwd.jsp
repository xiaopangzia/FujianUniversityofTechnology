<%--
  Created by IntelliJ IDEA.
  User: Cheng
  Date: 2018/4/6
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改密码</title>
    <link href="<c:url value="/jsps/css/user/changePwd.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="divMain">
    <h2>修改密码</h2>
    <form action="/UserServlet" method="post" id="formChangePwd">
        <input type="hidden" name="method" value="changePwd"/>
            <table>
                <tr>
                    <td><label id="msg">${msg}</label></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>旧密码：</td>
                    <td><input value="${user.loginpass}" class="inputClass" type="password" name="loginpass" id="loginpass"/></td>
                    <td><label id="loginpassError" class="Error">${error.loginpass}</label></td>
                </tr>
                <tr>
                    <td>新密码：</td>
                    <td><input value="${user.newloginpass}" class="inputClass" type="password" name="newloginpass" id="newloginpass"/></td>
                    <td><label id="newloginpassError" class="Error">${error.newloginpass}</label></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input value="${user.reloginpass}" class="inputClass" type="password" name="reloginpass" id="reloginpass"/></td>
                    <td><label id="reloginpassError" class="Error">${error.reloginpass}</label></td>
                </tr>
                <tr>
                    <td>验证码：</td>
                    <td><input class="inputClass" type="text" name="verifyCode" id="verifyCode"/></td>
                    <td><label id="verifyCodeError" class="Error">${error.verifyCode}</label></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <img id="imgVerifyCode" src="<c:url value='/VerifyCodeServlet'/>"/>
                        <a href="javascript:_changeImage()">看不清，换一张</a>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" value="修改密码"/></td>
                </tr>
            </table>
        </form>
    </div>
<script type="text/javascript" src="<c:url value="/jquery/jquery-3.3.1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jsps/js/user/changePwd.js"/>"></script>
</body>
</html>
