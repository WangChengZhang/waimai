package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.util.List;

public class ShopCat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3833334645733689724L;
	private Integer shopcatid;
	private String name;
	private List<Shop> shops;
	
	public ShopCat(){
		
	}
	
	public ShopCat(int shopcatid,String name){
		this.shopcatid = shopcatid;
		this.name = name;
	}
	
	public Integer getShopcatid() {
		return shopcatid;
	}

	public void setShopcatid(Integer shopcatid) {
		this.shopcatid = shopcatid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}
}
