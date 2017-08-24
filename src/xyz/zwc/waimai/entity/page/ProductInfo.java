package xyz.zwc.waimai.entity.page;

import java.math.BigDecimal;

import xyz.zwc.waimai.entity.Shop;

public class ProductInfo {
	private Integer productid;//物品id
	private String name;//物品名
	private String price;//物品合计价格
	private BigDecimal unitPrice;//物品单价
	private Integer count;//物品数量
	private Shop shop;//物品所属商店
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
