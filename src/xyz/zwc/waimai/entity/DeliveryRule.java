package xyz.zwc.waimai.entity;

import java.io.Serializable;

public class DeliveryRule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3401047413813250880L;
	private Integer deliveryRule;
	private String desciption;

	public Integer getDeliveryRule() {
		return deliveryRule;
	}

	public void setDeliveryRule(Integer deliveryRule) {
		this.deliveryRule = deliveryRule;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
}
