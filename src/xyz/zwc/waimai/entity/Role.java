package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3256241162102982255L;

	private Integer roleid;
	private String name;
	private String description;
	private Integer flag;
	private List<Function> functions;

	public Role() {

	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "role [roleid=" + roleid + ", name=" + name + ", description=" + description + ", flag=" + flag + "]";
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

}
