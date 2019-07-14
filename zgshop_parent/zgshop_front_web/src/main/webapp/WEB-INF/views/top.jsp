<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>

<div class="n-head">
    <div class="g-doc f-cb">
        <c:if test="${empty sessionScope.customer.name}">
            <div class="user">
                请<a href="${pageContext.request.contextPath}/front/customer/login">[登录]</a>
            </div>
            <ul class="nav">
                <li><a href="${pageContext.request.contextPath}/">首页</a></li>
            </ul>
        </c:if>
        <c:if test="${sessionScope.customer.id == 2}">
            <div class="user">
                买家你好，<span class="name">${sessionScope.customer.name}</span>！<a
                    href="${pageContext.request.contextPath}/front/customer/login">[退出]</a>
            </div>
            <ul class="nav">
                <li><a href="${pageContext.request.contextPath}/">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/front/account/selectAll">账务</a></li>
                <li><a href="${pageContext.request.contextPath}/front/shoppingCart/selectAll">购物车</a></li>
            </ul>
        </c:if>
        <c:if test="${sessionScope.customer.id == 1}">
            <div class="user">
                卖家你好，<span class="name">${sessionScope.customer.name}</span>！<a href="${pageContext.request.contextPath}/front/customer/login">[退出]</a>
            </div>
            <ul class="nav">
                <li><a href="${pageContext.request.contextPath}/">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/front/productManage/showProductManageMain">商品管理</a></li>
            </ul>

        </c:if>

    </div>
</div>