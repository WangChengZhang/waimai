package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4374373171768672685L;
	private Integer totalRecords;// 总记录数
	private Integer startPoint;// 当页记录查询起点
	private Integer pageSize = 10;// 每页记录限制数量
	private Integer totalpages;// 总页数
	private Integer currentPage;// 当前页
	private String keyWord;// 查询关键字
	private T keyWords;// 多条件查询实体
	private List<T> results;// 结果

	public void refresh() {
		if (totalRecords != null && totalRecords >= 0) {
			totalpages = (int) Math.ceil((double) totalRecords / (double) pageSize);
		}
		if (currentPage != null && currentPage > 0) {
			startPoint = (currentPage - 1) * pageSize;
		}
	}

	public Integer getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Integer startPoint) {
		this.startPoint = startPoint;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public T getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(T keyWords) {
		this.keyWords = keyWords;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Integer getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(Integer totalpages) {
		this.totalpages = totalpages;
	}

	@Override
	public String toString() {
		return "Page [totalRecords=" + totalRecords + ", startPoint=" + startPoint + ", pageSize=" + pageSize
				+ ", totalpages=" + totalpages + ", currentPage=" + currentPage + ", keyWord=" + keyWord + ", keyWords="
				+ keyWords + ", results=" + results + "]";
	}

}
