package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3831636137715106215L;
	private Integer orderid;
	private Integer fkOrderStatesState;
	private Integer fkCustAddressCustaddid;
	private Integer fkShopsShopid;
	private Integer fkDeliveryRulesDeliveryRule;
	private Integer fkUserUserid;
	private OrderState orderState;
	private CustAddress custAddress;
	private Shop shop;
	private DeliveryRule deliveryRule;
	private User user;
	private Timestamp createTime;
	private String custname;
	private String shopname;
	private BigDecimal totalPrice;
	private BigDecimal acPayment;
	private String phone;
	private String address;
	private List<OrderItem> orderItems;

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", fkOrderStatesState=" + fkOrderStatesState + ", fkCustAddressCustaddid="
				+ fkCustAddressCustaddid + ", fkShopsShopid=" + fkShopsShopid + ", fkDeliveryRulesDeliveryRule="
				+ fkDeliveryRulesDeliveryRule + ", fkUserUserid=" + fkUserUserid + ", orderState=" + orderState
				+ ", custAddress=" + custAddress + ", shop=" + shop + ", deliveryRule=" + deliveryRule + ", user="
				+ user + ", createTime=" + createTime + ", custname=" + custname + ", shopname=" + shopname
				+ ", totalPrice=" + totalPrice + ", acPayment=" + acPayment + ", phone=" + phone + ", address="
				+ address + ", orderItems=" + orderItems + "]";
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public CustAddress getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(CustAddress custAddress) {
		this.custAddress = custAddress;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public DeliveryRule getDeliveryRule() {
		return deliveryRule;
	}

	public void setDeliveryRule(DeliveryRule deliveryRule) {
		this.deliveryRule = deliveryRule;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getAcPayment() {
		return acPayment;
	}

	public void setAcPayment(BigDecimal acPayment) {
		this.acPayment = acPayment;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Integer getFkOrderStatesState() {
		return fkOrderStatesState;
	}

	public void setFkOrderStatesState(Integer fkOrderStatesState) {
		this.fkOrderStatesState = fkOrderStatesState;
	}

	public Integer getFkCustAddressCustaddid() {
		return fkCustAddressCustaddid;
	}

	public void setFkCustAddressCustaddid(Integer fkCustAddressCustaddid) {
		this.fkCustAddressCustaddid = fkCustAddressCustaddid;
	}

	public Integer getFkShopsShopid() {
		return fkShopsShopid;
	}

	public void setFkShopsShopid(Integer fkShopsShopid) {
		this.fkShopsShopid = fkShopsShopid;
	}

	public Integer getFkDeliveryRulesDeliveryRule() {
		return fkDeliveryRulesDeliveryRule;
	}

	public void setFkDeliveryRulesDeliveryRule(Integer fkDeliveryRulesDeliveryRule) {
		this.fkDeliveryRulesDeliveryRule = fkDeliveryRulesDeliveryRule;
	}

	public Integer getFkUserUserid() {
		return fkUserUserid;
	}

	public void setFkUserUserid(Integer fkUserUserid) {
		this.fkUserUserid = fkUserUserid;
	}
}
