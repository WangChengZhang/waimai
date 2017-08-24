package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.util.List;

public class ShopTag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1332294736530399217L;
	private Integer tagid;
	private String name;
	private List<Shop> shops;
	
	public ShopTag(){
		
	}
	
	public ShopTag(int tagid,String name){
		this.tagid = tagid;
		this.name = name;
	}

	public Integer getTagid() {
		return tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
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
