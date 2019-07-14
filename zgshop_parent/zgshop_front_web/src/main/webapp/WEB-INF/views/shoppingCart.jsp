<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>在线购物商城</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/neteasestyle.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>

    <script>
        function backMain() {
            $.post(
                '${pageContext.request.contextPath}/front/shoppingCart/backMain',
                function (result) {
                    location.href = '${pageContext.request.contextPath}/';
                }
            );
        }

        function showBuyModal() {
            $('#BuyModal').modal('show');
        }

        function buyProduct() {
            $.post(
                '${pageContext.request.contextPath}/front/order/payShoppingCart',
                function (result) {
                    alert(result.message);
                    location.href = '${pageContext.request.contextPath}/front/shoppingCart/selectAll';
                }
            );
        }

    </script>
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb" >
        <h2>已添加到购物车的内容</h2>
    </div>
    <table id="newTable" class="m-table m-table-row n-table g-b3">
        <thead>
        <tr><th>内容名称</th><th>数量</th><th>价格</th></tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="item">
            <tr>
                <td>${item.name}</td>
                <td>${item.num}</td>
                <td><span class="v-unit">¥</span><span class="value">${item.price}</span></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div id="act-btn">
        <button class="u-btn u-btn-primary" onclick="backMain()">退出</button>
        <button class="u-btn u-btn-primary" onclick="showBuyModal()">购买</button>
    </div>
</div>

<!-- 确认购买 start -->
<div class="modal fade" tabindex="-1" id="BuyModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-sm">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">提示消息</h4>
            </div>
            <div class="modal-body text-center">
                <h4>确认购买吗？</h4>
            </div>
            <div class="modal-footer">
                <%--<input type="hidden" id="deleteProductTypeId">--%>
                <button class="btn btn-primary updatePro" type="submit" data-dimiss="modal" onclick="buyProduct()">确定</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 确认购买 end -->

</body>
</html>