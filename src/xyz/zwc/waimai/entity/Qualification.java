package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Qualification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5926723836253431152L;
	private Integer qualificationid;
	private Date issueDate;
	private Date dueDate;
	private Integer flag;
	private List<File> files;

	public Integer getQualificationid() {
		return qualificationid;
	}

	public void setQualificationid(Integer qualificationid) {
		this.qualificationid = qualificationid;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
}
