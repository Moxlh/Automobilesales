<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>

<style>
    /* 基础设置 */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: Arial, sans-serif;
    }

    body {
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    /* 登录容器 */
    .login-container {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 100%;
    }

    .login-box {
        background: rgba(255, 255, 255, 0.8); /* 半透明白色背景 */
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        width: 400px;
        text-align: center;
    }

    /* 标题 */
    h1 {
        font-size: 28px;
        color: #333;
        margin-bottom: 10px;
    }

    h2 {
        font-size: 18px;
        color: #777;
        margin-bottom: 20px;
    }

    /* 警告信息 */
    .warn-msg {
        color: #e74c3c;
        margin-bottom: 15px;
        font-size: 14px;
    }

    /* 输入框 */
    .form-group {
        margin-bottom: 20px;
        text-align: left;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 16px;
        margin-top: 5px;
    }

    input[type="text"]:focus,
    input[type="password"]:focus {
        border-color: #4e73df;
        outline: none;
    }

    .password-hint {
        font-size: 12px;
        color: #888;
    }

    /* 身份选择 */
    .identity-group {
        text-align: left;
        margin-bottom: 20px;
    }

    .identity-group input {
        margin-right: 5px;
    }

    .identity-group span {
        margin-right: 20px;
        font-size: 14px;
        color: #333;
    }

    /* 按钮 */
    button {
        width: 100%;
        padding: 12px;
        background-color: #4e73df;
        border: none;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
        border-radius: 4px;
        transition: background-color 0.3s;
    }

    button:hover {
        background-color: #355c9b;
    }

    button:active {
        background-color: #2d487f;
    }

</style>

</head>

<body>
<div class="login-container">
    <div class="login-box">
        <h1>欢迎登录</h1>
        <h2>汽车销售管理系统</h2>
        <hr>
        <div class="warn-msg" id="warnMsg" data-warn-msg="${warnMsg}"></div>
        <form action="${pageContext.request.contextPath}/loginServlet?method=login" method="post" id="loginForm">
            <div class="form-group">
                <label for="name">用户名：</label>
                <input type="text" placeholder="请输入用户名" name="uName" id="name" required>
            </div>
            <div class="form-group">
                <label for="pwd">密码：</label>
                <input type="password" placeholder="请输入密码" name="uPwd" id="pwd" required>
                <span class="password-hint">请输入 6~20 位密码</span>
            </div>
            <div class="form-group identity-group">
                <label>请选择身份：</label>
                <input type="radio" value="employee" name="type" id="type1" checked><span>员工</span>
                <input type="radio" value="client" name="type" id="type2"><span>客户</span>
            </div>
            <button type="button" id="submitBtn">登录</button>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
    function msg() {
        var warnMsg = $("#warnMsg").data("warn-msg");
        if (warnMsg != null) {
            $("#warnMsg").html(warnMsg);
        }
    }

    $(document).ready(function () {
        msg();

        var flagName = false;
        var flagPwd = false;

        $("#name").change(function () {
            var name = $("#name").val();
            flagName = (name != null && name.trim() != "" && name.trim().length >= 3);
        });

        $("#pwd").change(function () {
            var pwd = $("#pwd").val();
            flagPwd = (pwd != null && pwd.trim().length >= 6 && pwd.trim().length <= 20);
        });

        $("#submitBtn").click(function () {
            if (flagPwd && flagName) {
                $("#loginForm").submit();
            } else {
                var errorMsg = "";
                if (!flagName) {
                    errorMsg += "请输入有效的用户名<br>";
                }
                if (!flagPwd) {
                    errorMsg += "请输入 6-20 位的有效密码<br>";
                }
                $("#warnMsg").html(errorMsg);
            }
        });
    });
</script>
</body>
</html>
