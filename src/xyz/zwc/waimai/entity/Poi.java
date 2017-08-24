package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Poi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -169224163929741154L;
	private String poiid;
	private String name;
	private String address;
	private Integer fkAdRegionsAdcode;
	private AdRegion adRegion;
	private BigDecimal longitude;
	private BigDecimal latitude;

	public String getPoiid() {
		return poiid;
	}

	public void setPoiid(String poiid) {
		this.poiid = poiid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AdRegion getAdRegion() {
		return adRegion;
	}

	public void setAdRegion(AdRegion adRegion) {
		this.adRegion = adRegion;
	}

	public Integer getFkAdRegionsAdcode() {
		return fkAdRegionsAdcode;
	}

	public void setFkAdRegionsAdcode(Integer fkAdRegionsAdcode) {
		this.fkAdRegionsAdcode = fkAdRegionsAdcode;
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

	@Override
	public String toString() {
		return "Poi [poiid=" + poiid + ", name=" + name + ", address=" + address + ", fkAdRegionsAdcode="
				+ fkAdRegionsAdcode + ", adRegion=" + adRegion + ", longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}

}
