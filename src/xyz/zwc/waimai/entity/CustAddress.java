package xyz.zwc.waimai.entity;

import java.io.Serializable;

public class CustAddress implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5156168386125957677L;
	private Integer custaddid;
	private Integer fkUsersUserid;
	private String fkPoisPoiid;
	private User user;
	private Poi poi;
	private String name;
	private Integer gender;
	private String address;
	private String phone;

	public Integer getCustaddid() {
		return custaddid;
	}

	public void setCustaddid(Integer custaddid) {
		this.custaddid = custaddid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Poi getPoi() {
		return poi;
	}

	public void setPoi(Poi poi) {
		this.poi = poi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getFkUsersUserid() {
		return fkUsersUserid;
	}

	public void setFkUsersUserid(Integer fkUsersUserid) {
		this.fkUsersUserid = fkUsersUserid;
	}

	public String getFkPoisPoiid() {
		return fkPoisPoiid;
	}

	public void setFkPoisPoiid(String fkPoisPoiid) {
		this.fkPoisPoiid = fkPoisPoiid;
	}
}
