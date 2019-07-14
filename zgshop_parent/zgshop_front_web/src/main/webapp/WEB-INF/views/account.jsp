<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>在线购物商城</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/neteasestyle.css"/>
</head>
<body>

<jsp:include page="top.jsp"/>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <table class="m-table m-table-row n-table g-b3" align="center">
        <colgroup>
            <col class="img"/>
            <col/>
            <col class="name">
            <col/>
            <col class="time"/>
            <col/>
            <col class="num"/>
            <col/>
            <col class="price"/>
            <col/>
        </colgroup>
        <thead>
        <tr>
            <td align="center">内容图片</td>
            <td align="center">内容名称</td>
            <td align="center">购买时间</td>
            <td align="center">购买数量</td>
            <td align="center">购买价格</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="accountItem">
            <tr>
                <td><a href="${pageContext.request.contextPath}/front/product/selectById?id=${accountItem.product_id}"><img
                        src="${pageContext.request.contextPath}/upload/${accountItem.image}" alt=""></a></td>
                <td><h4><a
                        href="${pageContext.request.contextPath}/front/product/selectById?id=${accountItem.product_id}">${accountItem.name}</a>
                </h4></td>
                <td><span class="v-time">${accountItem.date}</span></td>
                <td><span class="v-num">${accountItem.num}</span></td>
                <td><span class="v-unit">¥</span><span class="value">${accountItem.price}</span></td>
            </tr>
        </c:forEach>

        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="total">总计：</div>
            </td>
            <td><span class="v-unit">¥</span><span class="value">${total}</span></td>
        </tr>
        </tfoot>
    </table>
</div>
</body>
</html>
