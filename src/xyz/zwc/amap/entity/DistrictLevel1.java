package xyz.zwc.amap.entity;

public class DistrictLevel1 {
	private Integer citycode;
	private Integer adcode;
	private String name;
	private String center;
	private String level;

	@Override
	public String toString() {
		return "DistrictLevel1 [citycode=" + citycode + ", adcode=" + adcode + ", name=" + name + ", center=" + center
				+ ", level=" + level + "]";
	}

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

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

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
