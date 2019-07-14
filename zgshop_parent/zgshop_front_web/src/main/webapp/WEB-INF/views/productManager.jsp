<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>front</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>

    <script>
        $(function () {
            //上传图像预览
            $('#product-image').on('change', function () {
                $('#img').attr('src', window.URL.createObjectURL(this.files[0]));
            });
            $('#pro-image').on('change', function () {
                $('#img2').attr('src', window.URL.createObjectURL(this.files[0]));
            });

            $(function () {
                $('#pagination').bootstrapPaginator({
                    bootstrapMajorVersion: 3,
                    currentPage:${pageInfo.pageNum},
                    totalPages:${pageInfo.pages},
                    pageUrl: function (type, page, current) {
                        return '${pageContext.request.contextPath}/front/productManage/findAll?pageNum=' + page;
                    },
                    itemTexts: function (type, page, current) {
                        switch (type) {
                            case "first":
                                return "首页";
                            case "prev":
                                return "上一页";
                            case "next":
                                return "下一页";
                            case "last":
                                return "末页";
                            case "page":
                                return page;
                        }
                    }
                });
            });


            //表单校验
            $('#frmAddProduct').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    name:{
                        validators:{
                            notEmpty:{
                                message:'商品名称不能为空'
                            },
                            remote:{
                                type:'post',
                                url:'${pageContext.request.contextPath}/front/product/checkName'
                            }
                        }
                    },
                    price:{
                        validators:{
                            notEmpty:{
                                message:'商品价格不能为空'
                            },
                            regexp:{
                                regexp:/^\d+(\.\d+)?$/,
                                message:'商品价格格式不正确'
                            }
                        }
                    },
                    info:{
                        validators:{
                            notEmpty:{
                                message:'商品详情不能为空'
                            },
                            remote:{
                                type:'post',
                                url:'${pageContext.request.contextPath}/front/product/checkInfo'
                            }
                        }
                    },
                    file:{
                        validators:{
                            notEmpty:{
                                message:'请选择商品图片'
                            }
                        }
                    },
                    productTypeId:{
                        validators:{
                            notEmpty:{
                                message:'请选择商品类型'
                            }
                        }
                    }
                }
            });


            //服务端提示消息
            let successMsg = '${successMsg}';
            let errorMsg = '${errorMsg}';
            if (successMsg != '') {
                alert(successMsg);
            }
            if (errorMsg != '') {
                alert(errorMsg);
            }
        });


        //显示编辑商品的信息
        function showModifyModal(id) {
            $.post(
                '${pageContext.request.contextPath}/front/productManage/selectById',
                {'id': id},
                function (result) {
                    if (result.status == 1) {
                        $('#pro-num').val(result.data.id);
                        $('#pro-name').val(result.data.name);
                        $('#pro-price').val(result.data.price);
                        $('#pro-info').val(result.data.info);
                        $('#pro-type').val(result.data.productType.id);
                        $('#img2').attr('src', '${pageContext.request.contextPath}/front/productManage/getImage?path=' + result.data.image);
                    }
                }
            );
        }

        var deleteProductId;
        //显示确认删除的提示
        function showDeleteModal(id) {
            deleteProductId = id;
            $('#deleteProductModal').modal('show');
        }

        //删除商品
        function removeProduct() {
            $.post(
                '${pageContext.request.contextPath}/front/productManage/removeById',
                {'id': deleteProductId},
                function (result) {
                    alert(result.message);
                    $(function () {
                        //重新加载数据
                        location.href = '${pageContext.request.contextPath}/front/productManage/showProductManageMain';
                    })
                }
            );
        }

    </script>
</head>

<body>
<div class="panel panel-default" id="userPic">
    <div class="panel-body">
        <input type="button" value="发布商品" class="btn btn-primary" id="doAddPro">
        <br>
        <br>
        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <%--<th class="text-center">编号</th>--%>
                    <th class="text-center">商品</th>
                    <th class="text-center">价格(元)</th>
                    <th class="text-center">产品类型</th>
                    <th class="text-center">状态</th>
                    <%--<th class="text-center">出售数量</th>--%>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="product">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/front/product/selectById?id=${product.id}&tag=1">${product.name}</a></td>
                        <td>${product.price}</td>
                        <td>${product.productType.name}</td>
                        <td>
                            <c:if test="${product.sale_status == 0}">未出售</c:if>
                            <c:if test="${product.sale_status == 1}">已出售</c:if>
                                <%--未出售商品--%>
                        </td>
                        <td class="text-center">
                            <c:if test="${product.sale_status == 1}">
                                <input type="button" class="btn btn-warning btn-sm doProModify" value="编辑"
                                       disabled="disabled">
                                <%--<input type="button" class="btn btn-warning btn-sm doProDelete" value="删除"--%>
                                       <%--disabled="disabled" >--%>
                            </c:if>
                            <c:if test="${product.sale_status == 0}">
                                <input type="button" class="btn btn-warning btn-sm doProModify" value="编辑"
                                       onclick="showModifyModal(${product.id})">
                                <%--<input type="button" class="btn btn-warning btn-sm doProDelete" value="删除"--%>
                                       <%--onclick="showDeleteModal(${product.id})">--%>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav style="text-align: center">
                <%-- 使用bootstrap-paginator分页--%>
                <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 添加商品 start -->
<div class="modal fade" tabindex="-1" id="Product">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/front/productManage/add" class="form-horizontal" method="post"
              enctype="multipart/form-data" id="frmAddProduct">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <%--<h4 class="modal-title">添加商品</h4>--%>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="product-name" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="product-price" name="price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-price" class="col-sm-4 control-label">商品详情：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="product-info" name="info">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8">
                                <a href="javascript:" class="file">选择文件
                                    <input type="file" name="file" id="product-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" name="productTypeId">
                                    <option>请选择</option>
                                    <c:forEach items="${productTypes}" var="productTypes">
                                        <option value="${productTypes.id}">${productTypes.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 180px;height: 240px;" id="img">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">发布</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 添加商品 end -->

<!-- 编辑商品 start -->
<div class="modal fade" tabindex="-1" id="myProduct">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/front/productManage/modify" method="post"
              enctype="multipart/form-data" class="form-horizontal">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">编辑商品</h4>
                </div>
                <div class="modal-body text-center row">
                    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="pro-num" class="col-sm-4 control-label">商品编号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pro-num" name="id" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pro-name" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pro-price" name="price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-price" class="col-sm-4 control-label">商品详情：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pro-info" name="info">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8">
                                <a class="file">
                                    选择文件 <input type="file" name="file" id="pro-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="pro-type" name="productTypeId">
                                    <option>请选择</option>
                                    <c:forEach items="${productTypes}" var="productTypes">
                                        <option value="${productTypes.id}">${productTypes.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 180px;height: 240px;" id="img2">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary updatePro" type="submit">编辑</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 编辑商品 end -->

<!-- 删除商品 start -->
<div class="modal fade" tabindex="-1" id="deleteProductModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form class="form-horizontal">
        <%--<form class="form-horizontal" action="${pageContext.request.contextPath}/front/productManage/delete?id=" method="post" enctype="multipart/form-data">--%>
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">提示消息</h4>
                </div>
                <div class="modal-body text-center row">
                    <h4>确认要删除该商品吗</h4>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="deleteProductId">
                    <button class="btn btn-primary updatePro" onclick="removeProduct()">删除</button>
                    <%--<button class="btn btn-primary updatePro" type="submit">删除</button>--%>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>

            </div>
        </form>
    </div>
</div>
<!-- 删除商品 end -->

</body>

</html>