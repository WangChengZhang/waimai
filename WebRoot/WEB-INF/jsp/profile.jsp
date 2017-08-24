<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>个人资料-外卖网-网上订餐平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="订餐网上订餐,外卖,快餐外卖,外卖网">
<meta http-equiv="description" content="网上订餐平台">
<%@include file="/WEB-INF/commonjspf/common.jspf"%>
<link href="${proPath}/resource/css/aside.css" rel="stylesheet"
	type="text/css">
</head>

<body style="padding-top: 80px">
	<%@include file="/WEB-INF/commonjspf/navigator.jspf"%>
	<%@include file="/WEB-INF/commonjspf/aside.jspf"%>
	<div class="container" style="min-height: 500px">
		<div class="row">
			<div class="col-xs-3">
				<ul class="nav nav-pills nav-stacked">
					<li role="presentation"><a href="${proPath}/profile/order">我的订单</a></li>
					<li role="presentation"><a href="${proPath}/profile/address">我的地址</a></li>
					<li role="presentation" class="active"><a
						href="javascript:void(0);">个人资料</a></li>
				</ul>
			</div>
			<div class="col-xs-9">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="icon" class="col-sm-offset-3 col-sm-9"><img
							src="${proPath}/image?fileid=${user.fkFilesIcon}" alt="..."
							class="img-thumbnail" width="100" height="100"></label>
						<div class="col-sm-offset-3 col-sm-3">
							<input type="file" id="icon" name="image">选择图片文件，不大于100k<span
								class="text-danger iconinfo"></span>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default modifyimage">修改</button>
						</div>
					</div>
					<div class="form-group">
						<label for="username" class="col-sm-3 control-label">用户名</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="username"
								value="${user.name}" maxlength="15" disabled>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default modifyusername">修改</button>
						</div>
					</div>
					<div class="form-group">
						<label for="telephone" class="col-sm-3 control-label">手机号</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="telephone"
								value="${user.uqTelephone}" maxlength="11" disabled>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default modifyphone">修改</button>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">邮箱</label>
						<div class="col-sm-3">
							<input type="email" class="form-control" id="email"
								value="${user.uqEmail}" disabled>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default modifyemail">修改</button>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">修改密码:</label>
					</div>
					<div class="form-group">
						<label for="newpassword" class="col-sm-3 control-label">新密码</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" maxlength="40"
								id="newpassword">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpassword" class="col-sm-3 control-label">确认密码</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" maxlength="40"
								id="confirmpassword">
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default modifypassword">修改</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			var flag1 = 0;
			var flag2 = 0;
			var flag3 = 0;
	
			$(".modifyimage").click(function() {
				if ($("#icon").val().length > 0 && $("#icon").get(0).files[0].size > 0 && $("#icon").get(0).files[0].size <= 100 * 1024) {
					var filetype = $("#icon").val().substr($("#icon").val().lastIndexOf('.')).toLowerCase();
					if (filetype == ".jpg" || filetype == ".png" || filetype == ".gif" || filetype == ".bmp" || filetype == ".jpeg") {
						var file = $("#icon").get(0).files[0];
						var fd = new FormData();
						fd.append("image", file);
						$.ajax(
							{
								url : "${proPath}/profile/modifyicon",
								type : "POST",
								// 告诉jQuery不要去处理发送的数据
								processData : false,
								// 告诉jQuery不要去设置Content-Type请求头
								contentType : false,
								data : fd,
								beforeSend : function() {
									$(".iconinfo").text("正在上传。。。");
								},
								success : function(json) {
									$(".iconinfo").empty();
									if (json.result == "success") {
										alert("上传成功!");
										location.reload();
									} else if (json.result == "typeerror") {
										alert("格式必须为图片！");
									} else if (json.result == "sizeerror") {
										alert("请选择图片，大小不超过100k！");
									} else {
										alert("上传失败!");
									}
								},
								error : function() {
									$(".iconinfo").empty();
									alert("传输错误");
								},
								dataType : 'json'
							}
						);
					} else {
						$(".iconinfo").empty();
						alert("格式必须为图片！");
					}
				} else {
					$(".iconinfo").empty();
					alert("请选择图片，大小不超过100k！");
				}
			});
	
			$(".modifyusername").click(function() {
				if (flag1 > 0) {
					if ($('#username').val().length > 0) {
						$.post(
							"${proPath}/profile/modify",
							{
								username : $('#username').val()
							},
							function(json) {
								if (json.result == "success") {
									alert("修改成功");
									location.reload();
								} else if (json.result == "repeat") {
									alert("用户名已存在");
								}
							},
							"json"
						);
					} else {
						alert("请填写用户名");
					}
	
				}
				$("#username").removeAttr("disabled");
				flag1 = 1;
			});
	
			$(".modifyphone").click(function() {
				if (flag2 > 0) {
					if ($('#telephone').val().length == 11) {
						$.post(
							"${proPath}/profile/modify",
							{
								telephone : $('#telephone').val()
							},
							function(json) {
								if (json.result == "success") {
									alert("修改成功");
									location.reload();
								} else if (json.result == "repeat") {
									alert("手机号已存在");
								}
							},
							"json"
						);
					} else {
						alert("请填写手机号");
					}
	
				}
				$("#telephone").removeAttr("disabled");
				flag2 = 1;
			});
	
			$(".modifyemail").click(function() {
				if (flag3 > 0) {
					if ($('#email').val().length > 0) {
						$.post(
							"${proPath}/profile/modify",
							{
								email : $('#email').val()
							},
							function(json) {
								if (json.result == "success") {
									alert("修改成功");
									location.reload();
								} else if (json.result == "repeat") {
									alert("邮箱已存在");
								}
							},
							"json"
						);
					} else {
						alert("请填写邮箱");
					}
	
				}
				$("#email").removeAttr("disabled");
				flag3 = 1;
			});
	
			$(".modifypassword").click(function() {
				if ($("#newpassword").val() > 0 && $("#confirmpassword").val() > 0) {
					if ($("#newpassword").val() == $("#confirmpassword").val()) {
						$.post(
							"${proPath}/profile/modify",
							{
								password : $('#newpassword').val()
							},
							function(json) {
								if (json.result == "success") {
									alert("修改成功");
									location.reload();
								} else if (json.result == "passworderror") {
									alert("密码格式错误");
								}
							},
							"json"
						);
					} else {
						alert("前后密码不一致");
					}
				} else {
					alert("请填写密码");
				}
			});
	
		});
	</script>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>