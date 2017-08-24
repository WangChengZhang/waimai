<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="proPath" value="${pageContext.request.contextPath}" />
<div class="panel panel-default">
	<div class="panel-heading">添加新地址</div>
	<div class="panel-body">
		<div>
			<div class="input-group">
				<span class="input-group-addon">姓名</span> <input type="text"
					class="form-control username" maxlength="15" placeholder="请输入您的姓名">
			</div>
			<div class="input-group">
				<span class="input-group-addon">性别</span><span
					class="input-group-addon"> <input type="radio"
					class="gender" name="gender" value="1" checked> 先生
				</span> <span class="input-group-addon"> <input type="radio"
					class="gender" name="gender" value="2"> 女士
				</span>
			</div>
			<div class="input-group">
				<span class="input-group-addon">手机</span> <input type="text"
					class="form-control telephone" maxlength="11"
					placeholder="请输入您的手机号">
			</div>
			<div class="input-group">
				<span class="input-group-addon">位置</span> <input type="text"
					class="form-control" id="poi" placeholder="请输入小区、大厦或学校"> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-map-marker" aria-hidden="true"></span></span>
			</div>
			<div class="input-group">
				<span class="input-group-addon">详细地址</span> <input type="text"
					class="form-control inputaddress" maxlength="30"
					placeholder="单元、门牌号">
			</div>
			<input type="hidden" class="custaddid" value="">
		</div>
		<button type="button" class="btn btn-lg btn-primary saveaddress">保存</button>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		var poiid = null;

		AMap.plugin([ 'AMap.Autocomplete', 'AMap.PlaceSearch' ], function() {
			var autoOptions = {
				city : "", //城市，默认全国
				input : "poi", //使用联想输入的input的id
				datatype : "poi" //只搜索poi类型
			};
			autocomplete = new AMap.Autocomplete(autoOptions);
			AMap.event.addListener(autocomplete, "select", function(e) {
				//TODO 针对选中的poi实现自己的功能
				poiid = e.poi.id;
			});
		});

		$(".saveaddress").on("click", function() {
			if (poiid != null) {
				if ($(".username").val().length > 0 && $(".telephone").val().length > 0 && $(".inputaddress").val().length > 0) {
					var phone = /^[\d]{11}$/;
					if (phone.test($(".telephone").val())) {
						var param = {
							telephone : $(".telephone").val(),
							username : $(".username").val(),
							gender : $(".gender:checked").val(),
							poiid : poiid,
							address : $(".inputaddress").val(),
							custaddid : $(".custaddid").val()
						};
						$.post(
							"${proPath}/address/confirm", param, function(json) {
								if (json.result == "success") {
									alert("地址添加成功！");
									location.reload();
								} else if (json.result == "poinull") {
									$(".address-alert").remove();
									$(".saveaddress").before('<div class="alert alert-danger address-alert" role="alert">请填写具体的位置</div>');
								} else {
									$(".address-alert").remove();
									$(".saveaddress").before('<div class="alert alert-danger address-alert" role="alert">参数格式错误</div>');
								}
							}, "json"
						);

					} else {
						$(".address-alert").remove();
						$(".saveaddress").before('<div class="alert alert-danger address-alert" role="alert">手机号格式错误</div>');
					}
				} else {
					$(".address-alert").remove();
					$(".saveaddress").before('<div class="alert alert-danger address-alert" role="alert">请填写所有选项</div>');
				}
			} else {
				$(".address-alert").remove();
				$(".saveaddress").before('<div class="alert alert-danger address-alert" role="alert">请填写所有选项</div>');
			}
		});

	});
</script>