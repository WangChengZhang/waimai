package xyz.zwc.waimai.entity;

import java.io.Serializable;

public class ShopState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 214673843408796075L;
	private Integer shopState;
	private String description;

	public Integer getShopState() {
		return shopState;
	}

	public void setShopState(Integer shopState) {
		this.shopState = shopState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
