<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>在线购物商城</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/neteasestyle.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>

    <script>

        function deleteProduct(id) {
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/front/productManage/removeById',
                dataType: 'json',
                data: {'id': id},
                success: function (result) {
                    alert("success!");
                    $(function () {
                        //重新加载数据
                        location.href = '${pageContext.request.contextPath}/front/product/search';
                    })
                }
            });
        }
    </script>
</head>

<body>
<jsp:include page="top.jsp"/>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li class="z-sel"><a href="${pageContext.request.contextPath}/">所有内容</a></li>
                <c:if test="${not empty sessionScope.customer.name && sessionScope.customer.id == 2}">
                    <li><a href="${pageContext.request.contextPath}/front/product/selectValidProduct">未购买的内容</a></li>
                </c:if>
            </ul>
        </div>
    </div>

    <div class="n-plist">
        <ul class="f-cb" id="plist">
            <c:forEach items="${pageInfo.list}" var="product">
                <li>
                    <a href="${pageContext.request.contextPath}/front/product/selectById?id=${product.id}" class="link">
                        <div class="img"><img src="${pageContext.request.contextPath}/upload/${product.image}"
                                              alt="/image/${product.image}"></div>
                            <%--<div class="img"><img src="http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg" alt="/image/${product.image}"></div>--%>
                        <h3>${product.name}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${product.price}</span>
                        </div>
                        <c:if test="${product.sale_status == 1}">
                            <c:if test="${sessionScope.customer.id == 1}">
                                <span class="had"><b>已出售</b></span>
                            </c:if>
                            <c:if test="${sessionScope.customer.id == 2}">
                                <span class="had"><b>已购买</b></span>
                            </c:if>

                        </c:if>
                    </a>
                    <c:if test="${sessionScope.customer.id==1 && product.sale_status == 0}">
                        <input type="button" class="u-btn u-btn-normal u-btn-xs del" value="删除"
                               onclick="deleteProduct(${product.id})">
                    </c:if>

                </li>
            </c:forEach>

        </ul>
    </div>


</div>


</body>

</html>