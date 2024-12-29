<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>创建订单</title>
    <style>
        /* 页面基础样式 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .form-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 400px;
            box-sizing: border-box;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-size: 14px;
            color: #333;
            margin-bottom: 5px;
            display: block;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: #4e73df;
            outline: none;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #4e73df;
            border: none;
            color: white;
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

        /* 错误提示 */
        .error-msg {
            color: #e74c3c;
            font-size: 14px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>创建订单</h1>

    <!-- 错误消息显示 -->
    <div id="error-msg" class="error-msg"></div>

    <!-- 表单 -->
    <form action="${pageContext.request.contextPath}/sell_logServlet?method=addSell_log" method="post" id="subForm">
        <div class="form-group">
            <label for="cName">客户姓名：</label>
            <input type="text" id="cName" name="cName" placeholder="请输入客户姓名" required>
        </div>

        <div class="form-group">
            <label for="brand">车辆品牌：</label>
            <input type="text" id="brand" name="brand" placeholder="请输入车辆品牌" required>
        </div>

        <div class="form-group">
            <label for="model">车辆型号：</label>
            <input type="text" id="model" name="model" placeholder="请输入车辆型号" required>
        </div>

        <div class="form-group">
            <label for="color">车辆颜色：</label>
            <input type="text" id="color" name="color" placeholder="请输入车辆颜色" required>
        </div>

        <div class="form-group">
            <label for="num">购买数量：</label>
            <input type="number" id="num" name="num" placeholder="请输入购买数量" min="1" required>
        </div>

        <div class="form-group">
            <label for="money">订单价格：</label>
            <input type="number" id="money" name="money" placeholder="请输入订单价格" min="0" required>
        </div>

        <button type="submit">提交</button>
    </form>
</div>

<script>
    // 表单验证
    document.getElementById("subForm").addEventListener("submit", function(event) {
        var errorMsg = "";
        var cName = document.getElementById("cName").value;
        var brand = document.getElementById("brand").value;
        var model = document.getElementById("model").value;
        var color = document.getElementById("color").value;
        var num = document.getElementById("num").value;
        var money = document.getElementById("money").value;

        if (!cName || !brand || !model || !color || !num || !money) {
            errorMsg = "所有字段都是必填项！";
        }

        if (errorMsg) {
            event.preventDefault();
            document.getElementById("error-msg").textContent = errorMsg;
        }
    });
</script>
</body>
</html>
