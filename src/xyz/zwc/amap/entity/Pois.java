package xyz.zwc.amap.entity;

import java.io.Serializable;

public class Pois implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8015321199401968442L;
	private String id;
	private String name;
	private String type;
	private String typecode;
	private String address;
	private String location;
	private Integer citycode;
	private String cityname;
	private Integer adcode;
	private String adname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public Integer getAdcode() {
		return adcode;
	}

	public void setAdcode(Integer adcode) {
		this.adcode = adcode;
	}

	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	@Override
	public String toString() {
		return "Pois [id=" + id + ", name=" + name + ", type=" + type + ", typecode=" + typecode + ", address="
				+ address + ", location=" + location + ", citycode=" + citycode + ", cityname=" + cityname + ", adcode="
				+ adcode + ", adname=" + adname + "]";
	}
}
