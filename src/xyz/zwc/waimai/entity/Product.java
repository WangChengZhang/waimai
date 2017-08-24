package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1846530546155881649L;
	private Integer productid;
	private String name;
	private Integer fkShopsShopid;
	private Integer fkFilesIcon;
	private Integer fkFilesPicture;
	private Integer fkProductCatsPdcatid;
	private Shop shop;
	private File icon;
	private File picture;
	private ProductCat productCat;
	private BigDecimal price;
	private BigDecimal discount;
	private Integer monthlySale;
	private String description;
	private BigDecimal attitudeScore;
	private BigDecimal dishScore;
	private Timestamp createTime;
	private Integer flag;
	private List<Comment> comments;
	//给页面用
	private Integer dishScorePercent;
	private String simpleDisc;//简介

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public File getIcon() {
		return icon;
	}

	public void setIcon(File icon) {
		this.icon = icon;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public ProductCat getProductCat() {
		return productCat;
	}

	public void setProductCat(ProductCat productCat) {
		this.productCat = productCat;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getMonthlySale() {
		return monthlySale;
	}

	public void setMonthlySale(Integer monthlySale) {
		this.monthlySale = monthlySale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getFkShopsShopid() {
		return fkShopsShopid;
	}

	public void setFkShopsShopid(Integer fkShopsShopid) {
		this.fkShopsShopid = fkShopsShopid;
	}

	public Integer getFkFilesIcon() {
		return fkFilesIcon;
	}

	public void setFkFilesIcon(Integer fkFilesIcon) {
		this.fkFilesIcon = fkFilesIcon;
	}

	public Integer getFkFilesPicture() {
		return fkFilesPicture;
	}

	public void setFkFilesPicture(Integer fkFilesPicture) {
		this.fkFilesPicture = fkFilesPicture;
	}

	public Integer getFkProductCatsPdcatid() {
		return fkProductCatsPdcatid;
	}

	public void setFkProductCatsPdcatid(Integer fkProductCatsPdcatid) {
		this.fkProductCatsPdcatid = fkProductCatsPdcatid;
	}

	public Integer getDishScorePercent() {
		return dishScorePercent;
	}

	public void setDishScorePercent(Integer dishScorePercent) {
		this.dishScorePercent = dishScorePercent;
	}

	public String getSimpleDisc() {
		return simpleDisc;
	}

	public void setSimpleDisc(String simpleDisc) {
		this.simpleDisc = simpleDisc;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
