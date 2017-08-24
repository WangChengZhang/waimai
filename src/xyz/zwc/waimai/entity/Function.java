package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.util.List;

public class Function implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1249483683668188306L;
	private Integer functionid;
	private String name;
	private String description;
	private Integer fkViewsViewid;
	private View view;
	private Integer flag;
	private List<Role> roles;

	public Function() {

	}

	public Integer getFunctionid() {
		return functionid;
	}

	public void setFunctionid(Integer functionid) {
		this.functionid = functionid;
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

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Integer getFkViewsViewid() {
		return fkViewsViewid;
	}

	public void setFkViewsViewid(Integer fkViewsViewid) {
		this.fkViewsViewid = fkViewsViewid;
	}

}
