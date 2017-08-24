<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>

<title>${shop.name}-外卖网-网上订餐平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="订餐网上订餐,外卖,快餐外卖,外卖网">
<meta http-equiv="description" content="网上订餐平台">
<%@include file="/WEB-INF/commonjspf/common.jspf"%>
<script type="text/javascript">
	$(function() {
		AMap.plugin([ 'AMap.Autocomplete', 'AMap.PlaceSearch' ], function() {
			var autoOptions = {
				city : "", //城市，默认全国
				input : "poi" //使用联想输入的input的id
			};
			autocomplete = new AMap.Autocomplete(autoOptions);
			AMap.event.addListener(autocomplete, "select", function(e) {
				$("#poiid").val(e.poi.id);
			});
		});

		$(".addshop").click(function() {
			var file = $(".form1 .image").get(0).files[0];
			var fd = new FormData();
			fd.append("image", file);
			fd.append("name", $(".form1 .name").val());
			fd.append("fkShopCatsShopcatid", $(".form1 .fkShopCatsShopcatid:checked").val());
			$(".form1 .tagid:checked").each(function() {
				fd.append("tagid", $(this).val());
			});
			fd.append("fkPoisPoiid", $(".form1 .fkPoisPoiid").val());
			fd.append("fkShopStatesShopState", $(".form1 .fkShopStatesShopState:checked").val());
			fd.append("address", $(".form1 .address").val());
			fd.append("basePrice", $(".form1 .basePrice").val());
			fd.append("deliveryPrice", $(".form1 .deliveryPrice").val());
			fd.append("phone", $(".form1 .phone").val());
			fd.append("announcement", $(".form1 .announcement").val());

			$.ajax(
				{
					url : "${proPath}/admin/addshop",
					type : "POST",
					// 告诉jQuery不要去处理发送的数据
					processData : false,
					// 告诉jQuery不要去设置Content-Type请求头
					contentType : false,
					data : fd,
					success : function(html) {
						$("body").html(html);
					},
					dataType : 'html'
				}
			);
		});

	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<form action="${proPath}/admin/addshop"
					enctype="multipart/form-data" method="post" class="form1">
					<p>
						商店名 <input type="text" name="name" class="name" placeholder="name">
					</p>
					<p>
						<label> <input type="radio" name="fkShopCatsShopcatid"
							value="1" class="fkShopCatsShopcatid" id="fkShopCatsShopcatid_0">
							餐厅
						</label> <br> <label> <input type="radio"
							name="fkShopCatsShopcatid" value="2" class="fkShopCatsShopcatid"
							id="fkShopCatsShopcatid_1"> 超市商店
						</label> <br>
					</p>
					<table width="200">
						<tr>
							<td><label> <input type="checkbox" name="tagid"
									value="1" class="tagid" id="tagid_0"> 快餐便当
							</label></td>
						</tr>
						<tr>
							<td><label> <input type="checkbox" name="tagid"
									value="2" class="tagid" id="tagid_1"> 特色菜系
							</label></td>
						</tr>
						<tr>
							<td><label> <input type="checkbox" name="tagid"
									value="3" class="tagid" id="tagid_2"> 小吃夜宵
							</label></td>
						</tr>
						<tr>
							<td><label> <input type="checkbox" name="tagid"
									value="4" class="tagid" id="tagid_3"> 甜品饮品
							</label></td>
						</tr>
						<tr>
							<td><label> <input type="checkbox" name="tagid"
									value="5" class="tagid" id="tagid_4"> 果蔬生鲜
							</label></td>
						</tr>
						<tr>
							<td><label> <input type="checkbox" name="tagid"
									value="6" class="tagid" id="tagid_5"> 零食饮料
							</label></td>
						</tr>
					</table>
					<p></p>
					<p>
						<input type="file" class="image" name="image">
					</p>
					<p>
						poi地址 <input type="text" id="poi"> <input type="hidden"
							id="poiid" class="fkPoisPoiid" name="fkPoisPoiid">
					</p>
					<table width="200">
						<tr>
							<td><label> <input type="radio"
									name="fkShopStatesShopState" value="1"
									class="fkShopStatesShopState" id="fkShopStatesShopState_0">
									营业
							</label></td>
						</tr>
						<tr>
							<td><label> <input type="radio"
									name="fkShopStatesShopState" value="2"
									class="fkShopStatesShopState" id="fkShopStatesShopState_1">
									休息
							</label></td>
						</tr>
					</table>
					<p>
						详细地址 <input type="text" name="address" class="address"
							placeholder="address">
					</p>
					<p>
						起送价 <input type="number" name="basePrice" class="basePrice"
							placeholder="basePrice">
					</p>
					<p>
						配送费 <input type="number" name="deliveryPrice"
							class="deliveryPrice" placeholder="deliveryPrice">
					</p>
					<p>
						电话 <input type="text" name="phone" class="phone" maxlength="11">
					</p>
					<p>
						公告 <input type="text" name="announcement" class="announcement">
					</p>
					<input type="button" class="addshop" value="提交">
				</form>
			</div>
			<div class="col-xs-4">
				<form action="${proPath}/admin/addpcat">
					<p>
						商店id <input type="number" name="fkShopsShopid"
							placeholder="fkShopsShopid">
					</p>
					<p>
						商品种类名 <input type="text" name="name">
					</p>
					<input type="submit">
				</form>
			</div>
			<div class="col-xs-4">
				<form action="${proPath}/admin/addproduct"
					enctype="multipart/form-data" method="post">
					<p>
						商品名 <input type="text" name="name">
					</p>
					<p>
						商店id <input type="number" name="fkShopsShopid">
					</p>
					<p>
						商品分类id <input type="number" name="fkProductCatsPdcatid">
					</p>
					<input type="file" name="image">
					<p>
						价格 <input type="number" name="price">
					</p>
					<p>
						描述 <input type="text" name="description">
					</p>
					<input type="submit">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
