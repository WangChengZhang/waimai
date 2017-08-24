package xyz.zwc.waimai.entity.page;

import java.util.List;

import xyz.zwc.waimai.entity.ShopCat;
import xyz.zwc.waimai.entity.ShopTag;

public class Place {
	private ShopCat currentShopCat;
	private ShopTag currentShopTag;
	private List<ShopCat> shopCatList;
	private List<ShopTag> shopTagList;
	private List<ShopList> shopList;
	
	public List<ShopCat> getShopCatList() {
		return shopCatList;
	}
	public void setShopCatList(List<ShopCat> shopCatList) {
		this.shopCatList = shopCatList;
	}
	public List<ShopList> getShopList() {
		return shopList;
	}
	public void setShopList(List<ShopList> shopList) {
		this.shopList = shopList;
	}
	public List<ShopTag> getShopTagList() {
		return shopTagList;
	}
	public void setShopTagList(List<ShopTag> shopTagList) {
		this.shopTagList = shopTagList;
	}
	public ShopCat getCurrentShopCat() {
		return currentShopCat;
	}
	public void setCurrentShopCat(ShopCat currentShopCat) {
		this.currentShopCat = currentShopCat;
	}
	public ShopTag getCurrentShopTag() {
		return currentShopTag;
	}
	public void setCurrentShopTag(ShopTag currentShopTag) {
		this.currentShopTag = currentShopTag;
	}
	
}
