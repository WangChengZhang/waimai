<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>收货地址-外卖网-网上订餐平台</title>

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
					<li role="presentation" class="active"><a
						href="javascript:void(0);">我的地址</a></li>
					<li role="presentation"><a href="${proPath}/profile">个人资料</a></li>
				</ul>
			</div>
			<div class="col-xs-9">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xs-6">
							<c:forEach items="${addresses}" var="singleadd">
								<div class="panel panel-default">
									<div class="panel-body">
										<p>${singleadd.name}
											<c:choose>
												<c:when test="${singleadd.gender eq 1}">先生</c:when>
												<c:otherwise>女士</c:otherwise>
											</c:choose>
											手机:${singleadd.phone}
										</p>
										<p>${singleadd.poi.address},${singleadd.poi.name},${singleadd.address}</p>
									</div>
									<div class="collapse" id="add${singleadd.custaddid}">
										<div class="panel panel-default">
											<div class="panel-heading">修改地址</div>
											<div class="panel-body">
												<div>
													<div class="input-group">
														<span class="input-group-addon">姓名</span> <input
															type="text" class="form-control chusername"
															maxlength="15" value="${singleadd.name}">
													</div>

													<c:choose>
														<c:when test="${singleadd.gender eq 1}">
															<div class="input-group">
																<span class="input-group-addon">性别</span><span
																	class="input-group-addon"> <input type="radio"
																	class="chgender" name="gender${singleadd.custaddid}"
																	value="1" checked> 先生
																</span> <span class="input-group-addon"> <input
																	type="radio" class="chgender"
																	name="gender${singleadd.custaddid}" value="2">
																	女士
																</span>
															</div>
														</c:when>
														<c:otherwise>
															<div class="input-group">
																<span class="input-group-addon">性别</span><span
																	class="input-group-addon"> <input type="radio"
																	class="chgender" name="gender${singleadd.custaddid}"
																	value="1"> 先生
																</span> <span class="input-group-addon"> <input
																	type="radio" class="chgender"
																	name="gender${singleadd.custaddid}" value="2" checked>
																	女士
																</span>
															</div>
														</c:otherwise>
													</c:choose>

													<div class="input-group">
														<span class="input-group-addon">手机</span> <input
															type="text" class="form-control chtelephone"
															maxlength="11" value="${singleadd.phone}">
													</div>
													<div class="input-group">
														<span class="input-group-addon">位置</span> <input
															type="text" class="form-control"
															id="poi${singleadd.custaddid}" placeholder="请输入小区、大厦或学校">
														<span class="input-group-addon"><span
															class="glyphicon glyphicon-map-marker" aria-hidden="true"></span></span>
													</div>
													<div class="input-group">
														<span class="input-group-addon">详细地址</span> <input
															type="text" class="form-control chinputaddress"
															maxlength="30" value="${singleadd.address}">
													</div>
													<input type="hidden" class="chcustaddid"
														value="${singleadd.custaddid}"> <input
														type="hidden" class="poi${singleadd.custaddid} poiid"
														value="${singleadd.poi.poiid}">
												</div>
												<button type="button"
													class="btn btn-lg btn-primary changeaddress">确认修改</button>
											</div>
										</div>
									</div>
									<div class="panel-footer">
										<div class="container-fluid">
											<div class="row">
												<div class="col-xs-6">
													<button type="button" class="btn btn-primary"
														data-toggle="collapse"
														data-target="#add${singleadd.custaddid}">修改</button>
												</div>
												<input type="hidden" class="custaddid"
													value="${singleadd.custaddid}">
												<div class="col-xs-6">
													<button type="button" class="btn btn-primary deleteadd">删除</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="col-xs-6">
							<button type="button" class="btn btn-lg btn-primary btn-block"
								data-toggle="collapse" data-target="#addaddress">添加新地址</button>
							<div class="collapse" id="addaddress"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		$(function() {
			$("#addaddress").load("${proPath}/address/addresssave", function(responseText, textStatus, XMLHttpRequest) {
				/*
				alert(responseText); //请求返回的内容
				alert(textStatus); //请求状态：success，error
				alert(XMLHttpRequest); //XMLHttpRequest对象
				*/
			});
	
	
	
			AMap.plugin([ 'AMap.Autocomplete', 'AMap.PlaceSearch' ], function() {
			
				<c:forEach items="${addresses}" var="singleadd">
				var autoOption${singleadd.custaddid} = {
					city : "", //城市，默认全国
					input : "poi${singleadd.custaddid}", //使用联想输入的input的id
					datatype : "poi" //只搜索poi类型
				};
				autocomplete${singleadd.custaddid} = new AMap.Autocomplete(autoOption${singleadd.custaddid});
				AMap.event.addListener(autocomplete${singleadd.custaddid}, "select", function(e) {
					//TODO 针对选中的poi实现自己的功能
					$(".poi${singleadd.custaddid}").val(e.poi.id)
				});
				</c:forEach>
			
			});
	
			$(".changeaddress").on("click", function() {
				
					if ($(this).parent().find(".chusername").val().length > 0 && $(this).parent().find(".chtelephone").val().length > 0 && $(this).parent().find(".chinputaddress").val().length > 0) {
						var phone = /^[\d]{11}$/;
						if (phone.test($(this).parent().find(".chtelephone").val())) {
							var param = {
								telephone : $(this).parent().find(".chtelephone").val(),
								username : $(this).parent().find(".chusername").val(),
								gender : $(this).parent().find(".chgender:checked").val(),
								poiid : $(this).parent().find(".poiid").val(),
								address : $(this).parent().find(".chinputaddress").val(),
								custaddid : $(this).parent().find(".chcustaddid").val()
							};
							$.post(
								"${proPath}/address/confirm", param, function(json) {
									if (json.result == "success") {
										alert("地址修改成功！");
										location.reload();
									} else if (json.result == "poinull") {
										alert("请填写具体位置");
									} else {
										alert("参数格式错误");
									}
								}, "json"
							);
	
						} else {
							alert("手机号格式错误");
						}
					} else {
						alert("请填写所有选项");
					}
				
			});
			
			$(".deleteadd").click(function(){
				$.post("${proPath}/address/delete",{custaddid:$(this).parent().parent().find(".custaddid").val()},function(json){
					if(json.result=="success"){
						alert("删除成功！");
						location.reload();
					}
				},"json");
			});
	
		});
	</script>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>