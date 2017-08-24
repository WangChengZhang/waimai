package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1240941091492370547L;
	private Integer userid;
	private String name;
	private String password;
	private String uqTelephone;
	private String uqEmail;
	private Integer point;
	private Integer fkRolesRoleid;
	private Integer fkFilesIcon;
	private Role role;
	private File icon;
	private Timestamp createTime;
	private Integer flag;

	public User(){
		
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUqTelephone() {
		return uqTelephone;
	}

	public void setUqTelephone(String uqTelephone) {
		this.uqTelephone = uqTelephone;
	}

	public String getUqEmail() {
		return uqEmail;
	}

	public void setUqEmail(String uqEmail) {
		this.uqEmail = uqEmail;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getFkRolesRoleid() {
		return fkRolesRoleid;
	}

	public void setFkRolesRoleid(Integer fkRolesRoleid) {
		this.fkRolesRoleid = fkRolesRoleid;
	}

	public Integer getFkFilesIcon() {
		return fkFilesIcon;
	}

	public void setFkFilesIcon(Integer fkFilesIcon) {
		this.fkFilesIcon = fkFilesIcon;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public File getIcon() {
		return icon;
	}

	public void setIcon(File icon) {
		this.icon = icon;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}
