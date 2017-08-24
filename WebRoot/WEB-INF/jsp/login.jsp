<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>登陆-外卖网-网上订餐平台</title>

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
	margin-bottom: 5px;
}

.username, .password {
	height: 50px;
	width: 250px;
	margin-left: auto;
	margin-right: auto;
	border-radius: 5px;
	display: block;
	border: 2px solid #B5B6BB;
	margin-bottom: 10px;
}

.check {
	width: 250px;
	margin-left: auto;
	margin-right: auto;
	position: relative;
	height: 50px;
}

.checkpicture {
	display: block;
	position: absolute;
	top: 5px;
	border-radius: 3px;
	border: 1px solid #8E8E8E;
	overflow-x: hidden;
	overflow-y: hidden;
}

.refresh {
	height: 40px;
	display: block;
	position: absolute;
	left: 100px;
	top: 0px;
	width: 50px;
}

.checkcode {
	width: 90px;
	height: 40px;
	position: absolute;
	border-radius: 3px;
	left: 160px;
	border: 2px solid #B5B6BB;
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
		var redirect = '<c:choose><c:when test="${empty redirect}">${proPath}/place</c:when><c:otherwise>${redirect}</c:otherwise></c:choose>';

		$(":radio").click(function() {
			if ($(this).val() == "1") {
				$(".username").attr("placeholder", " 手机号");
			} else if ($(this).val() == "2") {
				$(".username").attr("placeholder", " 用户名");
			} else if ($(this).val() == "3") {
				$(".username").attr("placeholder", " 邮箱");
			}
		});

		$(".login").click(function() {
			if ($(".username").val().length > 0 && $(".password").val().length > 0 && $(".checkcode").val().length > 0) {
				$.post("${proPath}/login/verify", {
					username : $(".username").val(),
					type : $(".type:checked").val(),
					password : $(".password").val(),
					checkcode : $(".checkcode").val()
				}, function(json) {
					if (json.result == "checkcodeerror") {
						$(".alert-danger").remove();
						$(".check").after('<div class="alert alert-danger" role="alert">验证码错误！</div>');
					} else if (json.result == "passworderror") {
						$(".alert-danger").remove();
						$(".check").after('<div class="alert alert-danger" role="alert">用户名或密码错误！</div>');
					} else if (json.result == "success") {
						$(".alert-danger").remove();
						$(".check").after('<div class="alert alert-success" role="alert">登陆成功，2秒后跳转！</div>');
						setTimeout(function() {
							$(location).attr('href', redirect);
						}, 2000);
					}
				}, "json");
			} else {
				$(".alert-danger").remove();
				$(".check").after('<div class="alert alert-danger" role="alert">请填入所有内容！</div>');
			}
		});

		$(".refresh").click(function() {
			$(".checkpicture").attr("src", "${proPath}/image/checkcode?t=" + Math.random());
		});

		$(".register").click(function() {
			$(location).attr('href', "${proPath}/login/register");
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
					<span class="input-group-addon"> <input type="radio"
						class="type" name="type" value="1" checked> 手机号
					</span> <span class="input-group-addon"> <input type="radio"
						class="type" name="type" value="2"> 用户名
					</span><span class="input-group-addon"> <input type="radio"
						class="type" name="type" value="3"> 邮箱
					</span>
				</div>
				<input type="text" name="username" class="username"
					placeholder=" 手机号"> <input type="password" name="password"
					class="password" placeholder=" 密码">
				<div class="check">
					<img src="${proPath}/image/checkcode" alt="..." width="90"
						height="30" class="checkpicture" />
					<button type="button" class="btn btn-default refresh">刷新</button>
					<input type="text" name="checkcode" maxlength="4" class="checkcode"
						placeholder=" 输入验证码">
				</div>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-success login">登陆</button>
					<button type="button" class="btn btn-default register">注册</button>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>
