package xyz.zwc.waimai.entity;

import java.io.Serializable;

public class OrderItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2420618070821259224L;
	private Integer fkOrdersOrderid;
	private Integer fkProductsProductid;
	private Order order;
	private Product product;
	private Integer number;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getFkOrdersOrderid() {
		return fkOrdersOrderid;
	}

	public void setFkOrdersOrderid(Integer fkOrdersOrderid) {
		this.fkOrdersOrderid = fkOrdersOrderid;
	}

	public Integer getFkProductsProductid() {
		return fkProductsProductid;
	}

	public void setFkProductsProductid(Integer fkProductsProductid) {
		this.fkProductsProductid = fkProductsProductid;
	}
}
