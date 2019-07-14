<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
	<title>在线商城-后台管理系统</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css" />
	<script>
		//重新加载验证码
		function reloadImage(){
			$('#randCode').attr('src','${pageContext.request.contextPath}/backend/code/image?time='+new Date().getTime());
			$('#code').val('');
		}

		$(function(){
			$('#frmLogin').bootstrapValidator({
				feedbackIcons: {
					valid: 'glyphicon glyphicon-ok',
					invalid: 'glyphicon glyphicon-remove',
					validating: 'glyphicon glyphicon-refresh'
				},
				fields:{
					loginName:{
						validators:{
							notEmpty:{
								message:'用户名不能为空'
							}
						}
					},
					password:{
						validators:{
							notEmpty:{
								message:'密码不能为空'
							}
						}
					}
				}
			});

			//服务端提示消息
			let errorMsg='${errorMsg}';
			if(errorMsg!=''){
				layer.msg(errorMsg,{
					time:2000,
					skin:'errorMsg'
				});
			}
		});

	</script>
</head>
<body>
<!-- 使用自定义css样式 div-signin 完成元素居中-->
<div class="container div-signin">
	<div class="panel panel-primary div-shadow">
		<!-- h3标签加载自定义样式，完成文字居中和上下间距调整 -->
		<div class="panel-heading">
			<h3>在线商城系统</h3>
			<span>ZGSHOP</span>
		</div>
		<div class="panel-body">
			<!-- login form start -->
			<form action="${pageContext.request.contextPath}/backend/sysuser/login" class="form-horizontal" method="post" id="frmLogin">
				<div class="form-group">
					<label class="col-sm-3 control-label">用户名：</label>
					<div class="col-sm-9">
						<input class="form-control" type="text" placeholder="请输入用户名" name="loginName">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
					<div class="col-sm-9">
						<input class="form-control" type="password" placeholder="请输入密码" name="password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3">
					</div>
					<div class="col-sm-9 padding-left-0">
						<div class="col-sm-8">
							<button type="submit" class="btn btn-primary btn-block">登&nbsp;&nbsp;陆</button>
						</div>
					</div>
				</div>
			</form>
			<!-- login form end -->
		</div>
	</div>
</div>

</body>
</html>
