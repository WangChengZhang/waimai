package xyz.zwc.waimai.entity;

import java.io.Serializable;

public class AdLevel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4526994383796657696L;
	private String level;
	private String description;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
