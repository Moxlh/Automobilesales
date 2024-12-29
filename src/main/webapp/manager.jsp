<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>经理 - 管理系统</title>
    <style>
        /* 基础设置 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-top: 20px;
        }

        hr {
            border: 1px solid #ccc;
            width: 90%;
            margin: 20px auto;
        }

        /* 导航栏样式 */
        .navbar {
            background-color: #4e73df;
            padding: 10px 0;
            text-align: center;
        }

        .navbar a {
            color: #fff;
            margin: 0 15px;
            text-decoration: none;
            font-size: 16px;
            padding: 8px 16px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .navbar a:hover {
            background-color: #355c9b;
        }

        /* 表格样式 */
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4e73df;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        /* 操作按钮 */
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4e73df;
            color: white;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            transition: background-color 0.3s;
            margin-top: 20px;
        }

        .btn:hover {
            background-color: #355c9b;
        }

        .btn:active {
            background-color: #2d487f;
        }

        /* 弹性布局 */
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }

        .navbar {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<h1>欢迎 经理 ${user.getName()}</h1>
<hr>

<div class="container">
    <!-- 导航栏 -->
    <div class="navbar">
        <a href="${pageContext.request.contextPath}/managerServlet?method=getAllCars">查看车辆仓库</a>
        <a href="${pageContext.request.contextPath}/managerServlet?method=getMyLogs">查看我的订单</a>
        <a href="${pageContext.request.contextPath}/managerServlet?method=getMyClients">查看我的客户</a>
        <a href="${pageContext.request.contextPath}/managerServlet?method=getMyPeople">查看我的员工</a>
        <a href="${pageContext.request.contextPath}/sell_logServlet?method=addLog&type=employee">创建订单</a>
    </div>

    <hr>

    <!-- 根据 flag 显示不同的内容 -->
    <c:if test="${flag == 1}">
        <div class="table-container">
            <table>
                <tr>
                    <th>序号</th>
                    <th>品牌</th>
                    <th>型号</th>
                    <th>颜色</th>
                    <th>数量</th>
                    <th>价格</th>
                </tr>
                <c:forEach items="${cars}" var="car">
                    <tr>
                        <td>${car.getId()}</td>
                        <td>${car.getBrand()}</td>
                        <td>${car.getModel()}</td>
                        <td>${car.getColor()}</td>
                        <td>${car.getNum()}</td>
                        <td>${car.getMoney()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <c:if test="${flag == 2}">
        <div class="table-container">
            <table>
                <tr>
                    <th>客户名称</th>
                    <th>销售名称</th>
                    <th>品牌</th>
                    <th>型号</th>
                    <th>颜色</th>
                    <th>数量</th>
                    <th>总价</th>
                    <th>销售日期</th>
                </tr>
                <c:forEach items="${sellLogs}" var="sellLog">
                    <tr>
                        <td>${sellLog.getC_name()}</td>
                        <td>${sellLog.getP_name()}</td>
                        <td>${sellLog.getBrand()}</td>
                        <td>${sellLog.getModel()}</td>
                        <td>${sellLog.getColor()}</td>
                        <td>${sellLog.getNum()}</td>
                        <td>${sellLog.getMoney()}</td>
                        <td>${sellLog.getSell_date()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <c:if test="${flag == 3}">
        <div class="table-container">
            <table>
                <tr>
                    <th>客户id</th>
                    <th>客户姓名</th>
                    <th>客户性别</th>
                    <th>客户电话</th>
                </tr>
                <c:forEach items="${clients}" var="client">
                    <tr>
                        <td>${client.getId()}</td>
                        <td>${client.getName()}</td>
                        <td>${client.getGender()}</td>
                        <td>${client.getTelNum()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <c:if test="${flag == 4}">
        <div class="table-container">
            <table>
                <tr>
                    <th>员工id</th>
                    <th>员工姓名</th>
                    <th>员工性别</th>
                    <th>员工职位</th>
                    <th>员工电话</th>
                    <th>员工上级姓名</th>
                    <th>员工绩效</th>
                </tr>
                <c:forEach items="${people}" var="p">
                    <tr>
                        <td>${p.getId()}</td>
                        <td>${p.getName()}</td>
                        <td>${p.getGender()}</td>
                        <td>普通员工</td>
                        <td>${p.getTelNum()}</td>
                        <td>${p.getUpperName()}</td>
                        <td>${p.getSum()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>
