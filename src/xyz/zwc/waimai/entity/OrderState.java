package xyz.zwc.waimai.entity;

import java.io.Serializable;

public class OrderState implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5775846158087441717L;
	private Integer state;
	private String description;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
