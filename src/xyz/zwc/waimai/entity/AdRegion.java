package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class AdRegion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8820362771545485142L;
	private Integer adcode;
	private String name;
	private String fkAdLevelsLevel;
	private AdLevel adlevel;
	private Integer citycode;
	private BigDecimal longitude;
	private BigDecimal latitude;

	public Integer getAdcode() {
		return adcode;
	}

	public void setAdcode(Integer adcode) {
		this.adcode = adcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdLevel getAdlevel() {
		return adlevel;
	}

	public void setAdlevel(AdLevel adlevel) {
		this.adlevel = adlevel;
	}

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

	public String getFkAdLevelsLevel() {
		return fkAdLevelsLevel;
	}

	public void setFkAdLevelsLevel(String fkAdLevelsLevel) {
		this.fkAdLevelsLevel = fkAdLevelsLevel;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
}
