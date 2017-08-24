package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.util.List;

public class ProductCat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1987747786093085342L;
	private Integer pdcatid;
	private Integer fkShopsShopid;
	private Shop shop;
	private String name;
	private Integer flag;
	private List<Product> products;

	public Integer getPdcatid() {
		return pdcatid;
	}

	public void setPdcatid(Integer pdcatid) {
		this.pdcatid = pdcatid;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getFkShopsShopid() {
		return fkShopsShopid;
	}

	public void setFkShopsShopid(Integer fkShopsShopid) {
		this.fkShopsShopid = fkShopsShopid;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
