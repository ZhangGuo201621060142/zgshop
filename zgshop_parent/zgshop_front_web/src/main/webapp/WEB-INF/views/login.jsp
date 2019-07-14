<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>在线购物商城</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/neteasestyle.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>

    <script>
        $(function () {
                        //服务端提示消息
            let errorMsg = '${errorMsg}';
            if (errorMsg != '') {
                alert(errorMsg);
            }
        });
    </script>
</head>
<body>

<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            请<a href="${pageContext.request.contextPath}/front/customer/login">[登录]</a>
        </div>
        <ul class="nav">
            <li><a href="${pageContext.request.contextPath}/">首页</a></li>
        </ul>
    </div>
</div>
<form id="formLogin" action="${pageContext.request.contextPath}/front/customer/login" method="post"
      class="m-form m-form-ht n-login">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" name="loginName" autofocus/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" name="password"/>
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block">登 录</button>
        </div>
    </div>
</form>
</body>
</html>