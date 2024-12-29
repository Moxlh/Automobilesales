<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SManager</title>
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

    </style>
</head>

<body>
<!-- 欢迎信息 -->
<div class="container">
    <h1>欢迎 超级管理员 ${user.getName()}</h1>
    <hr>

    <!-- 导航栏 -->
    <div class="navbar">
        <a href="${pageContext.request.contextPath}/sManagerServlet?method=getAllCars">查看车辆仓库</a>
        <a href="${pageContext.request.contextPath}/sManagerServlet?method=getMyLogs">查看我的订单</a>
        <a href="${pageContext.request.contextPath}/sManagerServlet?method=getMyClients">查看我的客户</a>
        <a href="${pageContext.request.contextPath}/sManagerServlet?method=getMyPeople">查看我的员工</a>
        <a href="${pageContext.request.contextPath}/sManagerServlet?method=getAllPeople">查看所有员工</a>
        <a href="${pageContext.request.contextPath}/addLog.jsp">创建订单</a>
        <a href="${pageContext.request.contextPath}/sell_logServlet?method=selectAll">查看所有订单</a>
    </div>

<c:if test="${flag == 1}">
    <table border="1" cellspacing="0" width="800">
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
</c:if>

<c:if test="${flag == 2}">
    <table border="1" cellspacing="0" width="800">
        <tr>
            <th>订单id</th>
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
                <td>${sellLog.getId()}</td>
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
</c:if>

<c:if test="${flag == 3}">
    <table border="1" cellspacing="0" width="800">
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
</c:if>

<c:if test="${flag == 4}">
    <table border="1" cellspacing="0" width="800">
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
                <td>经理</td>
                <td>${p.getTelNum()}</td>
                <td>${p.getUpperName()}</td>
                <td>${p.getSum()}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${flag == 5}">
    <table border="1" cellspacing="0" width="800">
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
                <c:if test="${p.getPosition()==0}">
                    <td>普通员工</td>
                </c:if>
                <c:if test="${p.getPosition()==1}">
                    <td>经理</td>
                </c:if>
                <c:if test="${p.getPosition()==2}">
                    <td>总管</td>
                </c:if>
                <td>${p.getTelNum()}</td>
                <td>${p.getUpperName()}</td>
                <td>${p.getSum()}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${flag == 6}">
    <table border="1" cellspacing="0" width="800">
        <tr>
            <th>订单id</th>
            <th>客户名称</th>
            <th>销售名称</th>
            <th>品牌</th>
            <th>型号</th>
            <th>颜色</th>
            <th>数量</th>
            <th>总价</th>
            <th>销售日期</th>
            <th>状态</th>
            <th>审核</th>
        </tr>

        <c:forEach items="${sellLogs}" var="sellLog">
            <tr>
                <td>${sellLog.getId()}</td>
                <td>${sellLog.getC_name()}</td>
                <td>${sellLog.getP_name()}</td>
                <td>${sellLog.getBrand()}</td>
                <td>${sellLog.getModel()}</td>
                <td>${sellLog.getColor()}</td>
                <td>${sellLog.getNum()}</td>
                <td>${sellLog.getMoney()}</td>
                <td>${sellLog.getSell_date()}</td>
                <c:if test="${sellLog.getState()==0}">
                    <td>未审批</td>
                </c:if>
                <c:if test="${sellLog.getState()==1}">
                    <td>通过</td>
                </c:if>
                <c:if test="${sellLog.getState()==2}">
                    <td>驳回</td>
                </c:if>

                <c:if test="${sellLog.getState()==0}">
                    <td>
                        <a href="${pageContext.request.contextPath}/sell_logServlet?method=solveSellLog&id=${sellLog.getId()}&result=1">通过</a>
                        <a href="${pageContext.request.contextPath}/sell_logServlet?method=solveSellLog&id=${sellLog.getId()}&result=2">驳回</a>
                    </td>
                </c:if>

                <c:if test="${sellLog.getState()!=0}">
                    <td>无</td>
                </c:if>

            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
