<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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

        function showAddModal() {
            $('#addProductModal').modal('show');
        }

        function addProduct() {
            $.post(
                '${pageContext.request.contextPath}/front/product/addProduct2ShoppingCart',
                {'id': ${product.id}, 'num': $('#product-count').val()},
                function (result) {
                    alert(result.message);
                    //重新加载数据
                    location.href = '${pageContext.request.contextPath}/front/product/selectById?id=' + ${product.id};
                }
            );
        }

    </script>

</head>
<body>
<jsp:include page="top.jsp"/>
<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${pageContext.request.contextPath}/upload/${product.image}"></div>
        <div class="cnt">
            <h2>${product.name}</h2>
            <%--<p class="summary">测试</p>--%>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${product.price}</span>
            </div>
            <c:if test="${sessionScope.customer.id == 2}">
                <c:if test="${num!=null && num>0}">
                    <div class="num">购买数量：${num}
                    </div>
                </c:if>
                <c:if test="${num==null || num==0}">
                    <div class="num">购买数量：
                        <form>
                            <input type=button value="-" onClick="this.form.amount.value--;">
                            <input id="product-count" type=text size="1" name=amount value=1>
                            <input type=button value="+" onClick="this.form.amount.value++;">
                        </form>
                    </div>
                </c:if>

            </c:if>

            <c:if test="${sessionScope.customer.id == 1}">
                <div class="num">
                    出售数量：${num}
                </div>
            </c:if>

            <c:if test="${sessionScope.customer.id == 2}">
                <div class="oprt f-cb">
                    <c:if test="${product.sale_status == 1}">
                        <span class="u-btn u-btn-primary z-dis">已购买</span>
                        <span class="buyprice">当时购买价格：¥${product.price}</span>
                    </c:if>
                    <c:if test="${product.sale_status == 0}">
                        <button class="u-btn u-btn-primary" onclick="showAddModal(${product.id})">加入购物车</button>
                    </c:if>
                </div>
            </c:if>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${product.info}
    </div>
</div>

<!-- 确认删除 start -->
<div class="modal fade" tabindex="-1" id="addProductModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">提示消息</h4>
            </div>
            <div class="modal-body text-center">
                <h4>确认要将该商品加入购物车吗？</h4>
            </div>
            <div class="modal-footer">
                <%--<input type="hidden" id="deleteProductTypeId">--%>
                <button class="btn btn-primary updatePro" type="submit" data-dimiss="modal" onclick="addProduct()">确定
                </button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 确认删除 end -->

</body>
</html>