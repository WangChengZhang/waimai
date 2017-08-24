package xyz.zwc.waimai.entity.page;

import java.math.BigDecimal;

import xyz.zwc.waimai.entity.Shop;

public class ShopList {
	private Shop shop;
	private BigDecimal distance;//距离公里
	private Integer scorePercent;//表示菜品评分占100的多少/dishScore*20
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public BigDecimal getDistance() {
		return distance;
	}
	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}
	public Integer getScorePercent() {
		return scorePercent;
	}
	public void setScorePercent(Integer scorePercent) {
		this.scorePercent = scorePercent;
	}
	
}
