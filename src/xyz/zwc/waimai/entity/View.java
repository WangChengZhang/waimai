package xyz.zwc.waimai.entity;

import java.io.Serializable;

public class View implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7172783927361729718L;
	private Integer viewid;
	private String path;
	private String description;
	private Integer flag;

	public Integer getViewid() {
		return viewid;
	}

	public void setViewid(Integer viewid) {
		this.viewid = viewid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
