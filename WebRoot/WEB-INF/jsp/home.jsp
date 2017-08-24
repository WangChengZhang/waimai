<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>外卖网-网上订餐平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="订餐网上订餐,外卖,快餐外卖,外卖网">
<meta http-equiv="description" content="网上订餐平台">

<%@include file="/WEB-INF/commonjspf/common.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${proPath}/resource/css/home.css">
<script type="text/javascript">
	$(function() {
		//关键变量
		var city = "${requestScope.city}";
		var adcode = <c:choose><c:when test="${not empty requestScope.adcode}">${requestScope.adcode}</c:when><c:otherwise>110000</c:otherwise></c:choose>;
		var poiid = null;

		//初始化关闭城市列表
		$(".dropdown-panel").slideUp(0);
		$(".dropdown-city").slideUp(0);
		$(".dropdown-poi").slideUp(0);

		//初始化城市
		$(".btn-city").html(city + '<span class="caret"></span>');

		//加载高德地图城市列表
		AMap.service('AMap.DistrictSearch', function() { //回调函数
			var districtSearch = new AMap.DistrictSearch({
				level : 'city',
				extensions : 'base',
				subdistrict : 3, //只显示市与下一级
				showbiz : 'false'
			});
			var letters = [ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z' ];
			var dropdown = $(".dropdown-panel > .panel-danger > .panel-body");
			//调用查询方法
			districtSearch.search("中国", function(status, result) {
				if (status == 'complete') {
					var subDistricts = result.districtList[0].districtList;
					for (var i = 0; i < 23; i++) {
						dropdown.append('<table class="table"><tr class="bg-primary"><th>' + letters[i] + '</th></tr><tr><td><div class="row">');
						for (var j = 0; j < subDistricts.length; j++) {
							if (subDistricts[j].districtList != null) {
								for (var k = 0; k < subDistricts[j].districtList.length; k++) {
									if (pinyin.getCamelChars(subDistricts[j].districtList[k].name.charAt(0)) == letters[i]) {

										dropdown.append('<div class="col-sm-2 col-md-2 col-lg-2"><a class="btn btn-default btn-block" value="' + subDistricts[j].districtList[k].adcode + '">' + subDistricts[j].districtList[k].name + '</a></div>');
									}
									if (subDistricts[j].districtList[k].districtList != null) {
										for (var l = 0; l < subDistricts[j].districtList[k].districtList.length; l++) {
											if (pinyin.getCamelChars(subDistricts[j].districtList[k].districtList[l].name.charAt(0)) == letters[i]) {

												dropdown.append('<div class="col-sm-2 col-md-2 col-lg-2"><a class="btn btn-default btn-block" value="' + subDistricts[j].districtList[k].districtList[l].adcode + '">' + subDistricts[j].districtList[k].districtList[l].name + '</a></div>');
											}
										}
									}
								}
							}
						}
						dropdown.append('</div></td></tr></table>');
					}
				}
			});
		});

		//加载地图
		var map = new AMap.Map('container', {
			center : [ 117.000923, 36.675807 ],
			zoom : 6
		});
		map.plugin([ "AMap.ToolBar", 'AMap.Scale', 'AMap.OverView' ], function() {
			map.addControl(new AMap.ToolBar());
			map.addControl(new AMap.Scale());
			map.addControl(new AMap.OverView({
				isOpen : true
			}));
		});
		map.setCity(city);
		//城市变化时改变地图
		AMap.event.addDomListener(document.getElementsByClassName("dropdown-panel")[0], 'click', function() {
			map.setCity(city);
		});
		AMap.event.addDomListener(document.getElementsByClassName("dropdown-city")[0], 'click', function() {
			map.setCity(city);
		});

		//选择城市 由于是后生成的元素用动态绑定 用简单选择器
		$(".dropdown-panel").on("click", "a", function() {
			city = $(this).text();
			$(".btn-city").html(city + '<span class="caret"></span>');
			adcode = $(this).attr("value");
			$(".dropdown-panel").slideUp();
			AMap.event.addDomListener(document.getElementsByClassName("dropdown-panel")[0], 'click', function() {
				map.setCity(city);
			});
		});

		//显示城市列表
		$(".btn-city").click(function(e) {
			$(".dropdown-panel").slideToggle();
			$(".dropdown-poi").empty();
			$(".dropdown-city").empty();
			e.stopPropagation(); //停止传播点击事件				
		});
		//关闭城市列表
		$(document).click(function() {
			$(".dropdown-panel").slideUp();
		});
		//防止点击城市列表关闭
		$(".dropdown-panel").click(function(e) {
			e.stopPropagation(); //停止传播
		});

		//选择猜测城市
		$(".guess-city").click(function(e) {
			city = $(this).text();
			adcode = $(this).val();
			$(".btn-city").html(city + '<span class="caret"></span>');
			$(".dropdown-panel").slideUp();
		});

		//搜索城市
		$(".city-input").keyup(function() {

			$(".dropdown-city").empty();
			AMap.plugin('AMap.Autocomplete', function() { //回调函数
				//实例化Autocomplete
				var autoOptions = {
					type : "190103|190104|190105", //类型，只搜索直辖市，地级市，区县
					city : "", //城市，默认全国
				};
				autocomplete = new AMap.Autocomplete(autoOptions);
				//TODO: 使用autocomplete对象调用相关功能
				autocomplete.search($(".city-input").val(), function(status, result) {
					//TODO:开发者使用result自己进行下拉列表的显示与交互功能
					if (status == "complete" && result.count > 0) {
						var length = ((result.count > 8) ? 8 : result.count);
						for (var i = 0; i < length; i++) { //最多显示8条
							$(".dropdown-city").append('<li class="list-group-item" value="' + result.tips[i].adcode + '">' + result.tips[i].name + '</li>');
						}
						$(".dropdown-city").slideDown(0);
					}
				});
			});
		});

		//选择搜索到的城市 由于是后生成的元素用动态绑定 用简单选择器
		$(".dropdown-city").on("click", "li", function() {
			city = $(this).text();
			adcode = $(this).val();
			$(".btn-city").html(city + '<span class="caret"></span>');
			$(".dropdown-panel").slideUp();
			AMap.event.addDomListener(document.getElementsByClassName("dropdown-city")[0], 'click', function() {
				map.setCity(city);
			});
		});

		//搜索poi
		$(".poi-input").keyup(function() {

			$(".dropdown-poi").empty();
			if (adcode != null) {
				AMap.service('AMap.PlaceSearch', function() { //回调函数

					var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
						pageSize : 10,
						pageIndex : 1,
						city : '' + city + '', //城市或地区
						citylimit : 'true', //限定城市
						type : '汽车服务|汽车销售|汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|金融保险服务|公司企业|道路附属设施|公共设施',
						map : map
					});
					//关键字查询
					placeSearch.search($(".poi-input").val(), function(status, result) {
						if (status == "complete" && result.poiList.pageSize > 0) {
							for (var i = 0; i < result.poiList.pageSize; i++) { //最多显示10条
								$(".dropdown-poi").append('<li class="list-group-item" value="' + result.poiList.pois[i].id + '"><h4>' + result.poiList.pois[i].name + '<small>' + result.poiList.pois[i].address + '</small></h4></li>');
							}
							$(".dropdown-poi").slideDown(0);
						}
					});
				});
			}
		});

		//选择poi 由于是后生成的元素用动态绑定 用简单选择器
		$(".dropdown-poi").on("click", "li", function() {
			poiid = $(this).attr('value');
			$(".poi-input").val($(this).text());
			$(".dropdown-poi").slideUp(0);
		});
		
		//确认搜索地址
		$(".poi-search").click(function(){
			if(poiid==null){
				return false;
			}
			else{
				$(location).attr('href', '${proPath}/place?poiid='+poiid);
			}
		});
	});
</script>
</head>

<body>
	<div class="jumbotron home">
		<h1 class="text-center">外卖网</h1>
		<p class="text-center">网上订餐 快餐外卖</p>
		<div id="container" class="center-block"
			style="width:500px; height:300px"></div>
		<div class="container">
			<div class="row">
				<div
					class="col-sm-8 col-md-8 col-lg-8 col-sm-offset-2 col-md-offset-2 col-lg-offset-2">
					<div class="input-group">
						<div class="input-group-btn">
							<button type="button" class="btn btn-default btn-city">
								${requestScope.city}<span class="caret"></span>
							</button>
						</div>
						<input type="text" class="form-control poi-input"
							placeholder="请输入你的收货地址（写字楼，小区，街道或者学校）">
						<div class="drop">
							<ul class="list-group dropdown-poi">
								
							</ul>
						</div>
						<span class="input-group-btn">
							<button class="btn btn-danger btn-search poi-search" type="button">搜索</button>
						</span>
					</div>
					<!-- /btn-group -->
				</div>
			</div>
			<div class="row dropdown-panel">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<div class="row">
							<div class="col-sm-7 col-md-7 col-lg-7">
								<div class="input-group">
									<span class="input-group-addon">请选择您所在的城市&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
										class="text-primary">猜你在</span></span> <span class="input-group-btn">
										<button class="btn btn-warning guess-city" type="button"
											value="${requestScope.adcode}">${requestScope.city}</button>
									</span>
									<div class="drop">
										<input type="text"
											class="dropdown-toggle form-control city-input"
											placeholder="请输入城市">
										<ul class="list-group dropdown-city">
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-body"></div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/commonjspf/footer.jspf"%>
</body>
</html>
