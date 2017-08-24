package xyz.zwc.amap.entity;

public class AdcodeParams {
	private String key;
	private String keywords;//只支持单个关键词语搜索关键词支持：行政区名称、citycode、adcode
	private Integer subdistrict=0;//只搜索citycode或adcode
	private String showbiz="false";
	private String extensions = "base";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(Integer subdistrict) {
		this.subdistrict = subdistrict;
	}

	public String getShowbiz() {
		return showbiz;
	}

	public void setShowbiz(String showbiz) {
		this.showbiz = showbiz;
	}

	public String getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}
}
