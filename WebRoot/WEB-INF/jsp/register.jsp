<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>注册-外卖网-网上订餐平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="订餐网上订餐,外卖,快餐外卖,外卖网">
<meta http-equiv="description" content="网上订餐平台">
<%@include file="/WEB-INF/commonjspf/common.jspf"%>
<style type="text/css">
body {
	font-family: "黑体";
}

.jumbotron.head {
	color: #FFFFFF;
	background-color: #0391E6;
}

.container {
	margin-bottom: 50px;
}

.input-group {
	width: 250px;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 20px;
}

.input-group * {
	height: 40px;
}

.alert {
	width: 250px;
	margin-left: auto;
	margin-right: auto;
}

.btn-group {
	margin-left: auto;
	margin-right: auto;
	width: 250px;
	display: block;
}

.login {
	width: 125px;
}

.register {
	width: 125px;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".login").click(function() {
			$(location).attr('href', "${proPath}/login");
		});

		$(".register").click(function() {
			if ($(".password").val().length > 0 && $(".passwordconfirm").val().length > 0 && $(".telephone").val().length > 0) {
				if ($(".password").val() == $(".passwordconfirm").val()) {
					var telephone = $(".telephone").val();
					var password = $(".password").val();
					var username = null;
					var mail = null;
					if ($(".username").val().length > 0) {
						username = $(".username").val();
					}
					if ($(".mail").val().length > 0) {
						mail = $(".mail").val();
					}
					var param = {
						telephone : telephone,
						username : username,
						password : password,
						mail : mail
					};
					$.post(
						"${proPath}/login/register/verify", param, function(json) {
							if (json.result == "1") {
								$(".alert").remove();
								$(".btn-group").before('<div class="alert alert-danger" role="alert">用户名重复或错误</div>');
							} else if (json.result == "2") {
								$(".alert").remove();
								$(".btn-group").before('<div class="alert alert-danger" role="alert">手机号重复或错误</div>');
							} else if (json.result == "3") {
								$(".alert").remove();
								$(".btn-group").before('<div class="alert alert-danger" role="alert">邮箱重复或错误</div>');
							} else if (json.result == "4") {
								$(".alert").remove();
								$(".btn-group").before('<div class="alert alert-danger" role="alert">密码格式错误</div>');
							} else if (json.result == "5") {
								$(".alert").remove();
								$(".btn-group").before('<div class="alert alert-success" role="alert">注册成功，请登录！3秒后跳转！</div>');
								setTimeout(function() {
									$(location).attr('href', "${proPath}/login");
								}, 3000);
							}
						}, "json"
					);

				} else {
					$(".alert").remove();
					$(".btn-group").before('<div class="alert alert-danger" role="alert">密码填写不一致</div>');
				}
			} else {
				$(".alert").remove();
				$(".btn-group").before('<div class="alert alert-danger" role="alert">请将带*的选项填写完整</div>');
			}
		});
	});
</script>
</head>
<body>
	<div class="jumbotron head">
		<h1 class="text-center">
			<a href="${proPath}/place" style="text-decoration: none;"><font
				color="#FFFFFF">外卖网</font></a>
		</h1>
		<p class="text-center">网上订餐 快餐外卖</p>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="input-group">
					<span class="input-group-addon">手机<span class="text-danger">*</span></span>
					<input type="text" maxlength="11" class="form-control telephone"
						placeholder="手机">
				</div>
				<div class="input-group">
					<span class="input-group-addon">用户名</span> <input type="text"
						class="form-control username" maxlength="15" placeholder="用户名">
				</div>
				<div class="input-group">
					<span class="input-group-addon">邮箱</span> <input type="email"
						class="form-control mail" placeholder="邮箱">
				</div>
				<div class="input-group">
					<span class="input-group-addon">密码(字母数字)<span
						class="text-danger">*</span></span> <input type="password" maxlength="40"
						class="form-control password" placeholder="密码">
				</div>
				<div class="input-group">
					<span class="input-group-addon">确认密码<span
						class="text-danger">*</span></span> <input type="password"
						class="form-control passwordconfirm" maxlength="40"
						placeholder="确认密码">
				</div>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-default login">登陆</button>
					<button type="button" class="btn btn-success register">注册</button>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>
