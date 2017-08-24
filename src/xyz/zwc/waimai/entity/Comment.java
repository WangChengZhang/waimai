package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6990220642176685708L;
	private Integer fkOrdersOrderid;
	private Integer fkUsersUserid;
	private Integer fkProductsProductid;
	private Order order;
	private User user;
	private Product product;
	private String comment;
	private BigDecimal attitudeScore;
	private BigDecimal dishScore;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getAttitudeScore() {
		return attitudeScore;
	}

	public void setAttitudeScore(BigDecimal attitudeScore) {
		this.attitudeScore = attitudeScore;
	}

	public BigDecimal getDishScore() {
		return dishScore;
	}

	public void setDishScore(BigDecimal dishScore) {
		this.dishScore = dishScore;
	}

	public Integer getFkOrdersOrderid() {
		return fkOrdersOrderid;
	}

	public void setFkOrdersOrderid(Integer fkOrdersOrderid) {
		this.fkOrdersOrderid = fkOrdersOrderid;
	}

	public Integer getFkUsersUserid() {
		return fkUsersUserid;
	}

	public void setFkUsersUserid(Integer fkUsersUserid) {
		this.fkUsersUserid = fkUsersUserid;
	}

	public Integer getFkProductsProductid() {
		return fkProductsProductid;
	}

	public void setFkProductsProductid(Integer fkProductsProductid) {
		this.fkProductsProductid = fkProductsProductid;
	}

	@Override
	public String toString() {
		return "Comment [fkOrdersOrderid=" + fkOrdersOrderid + ", fkUsersUserid=" + fkUsersUserid
				+ ", fkProductsProductid=" + fkProductsProductid + ", order=" + order + ", user=" + user + ", product="
				+ product + ", comment=" + comment + ", attitudeScore=" + attitudeScore + ", dishScore=" + dishScore
				+ "]";
	}
}
