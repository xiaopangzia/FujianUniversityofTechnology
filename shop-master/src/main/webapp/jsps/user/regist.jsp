<%--
  Created by IntelliJ IDEA.
  User: Cheng
  Date: 2018/4/1
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>注册</title>
    <link href="<c:url value='/jsps/css/user/regist.css'/> " rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="divMain">
        <div id="divTitle">
            <span id="spanTitle">新用户注册</span>
        </div>
        <div id="divBody">
            <form action="<c:url value='/UserServlet'/>" method="post" id="registForm">
                <input type="hidden" name="method" value="regist" />
                <table id="tableForm">
                    <tr>
                        <td class="tdText">用户名：</td>
                        <td class="tdInput">
                            <input class="inputClass" type="text" name="loginname" id="loginname" value="${form.loginname}"/>
                        </td>
                        <td class="tdError">
                            <label class="errorClass" id="loginnameError">${errors.loginname}</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="tdText">登录密码：</td>
                        <td>
                            <input class="inputClass" type="password" name="loginpass" id="loginpass" value="${form.loginpass}"/>
                        </td>
                        <td><label class="errorClass" id="loginpassError">${errors.loginpass}</label></td>
                    </tr>
                    <tr>
                        <td class="tdText">确认密码：</td>
                        <td>
                            <input class="inputClass" type="password" name="reloginpass" id="reloginpass" value="${form.reloginpass}"/>
                        </td>
                        <td><label class="errorClass" id="reloginpassError">${errors.reloginpass}</label></td>
                    </tr>
                    <tr>
                        <td class="tdText">Email：</td>
                        <td>
                            <input class="inputClass" type="text" name="email" id="email" value="${form.email}"/>
                        </td>
                        <td><label class="errorClass" id="emailError">${errors.email}</label></td>
                    </tr>
                    <tr>
                        <td class="tdText">图形验证码：</td>
                        <td>
                            <input class="inputClass" type="text" name="verifyCode" id="verifyCode" value="${form.verifyCode}"/>
                        </td>
                        <td><label class="errorClass" id="verifyCodeError">${errors.verifyCode}</label></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <div id="divVerifyCode"><img id="imgVerifyCode" src="<c:url value='/VerifyCodeServlet'/> " /></div>
                        </td>
                        <td><label><a href="javascript:_changeImage()">换一张</a></label></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input class="inputClass" type="image" src="<c:url value='/images/regist1.jpg'/>" id="submitBtn"/>
                        </td>
                        <td><label></label></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value="/jquery/jquery-3.3.1.js"/> "></script>
    <script type="text/javascript" src="<c:url value="/jsps/js/user/regist.js"/> "></script>
</body>
</html>
