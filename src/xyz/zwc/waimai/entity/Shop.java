package xyz.zwc.waimai.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xyz.zwc.waimai.entity.page.ProductInfo;
import xyz.zwc.waimai.util.Location;

public class Shop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3092106888618830493L;

	private Integer shopid;
	private String name;
	private Integer fkShopCatsShopcatid;
	private Integer fkUsersUserid;
	private Integer fkFilesIcon;
	private String fkPoisPoiid;
	private Integer fkQualificationsQualificationid;
	private Integer fkDeliveryRulesDeliveryRule;
	private Integer fkShopStatesShopState;
	private ShopCat shopcat;
	private User user;
	private File icon;
	private Poi poi;
	private Qualification qualification;
	private DeliveryRule deliveryRule;
	private ShopState shopState;
	private String address;
	private BigDecimal basePrice;
	private BigDecimal deliveryPrice;
	private Integer averageTime;
	private Time openTime;
	private Time closeTime;
	private String phone;
	private String announcement;
	private BigDecimal attitudeScore;
	private BigDecimal dishScore;
	private Integer monthlySale;
	private Timestamp createTime;
	private Integer flag;
	private List<ShopTag> shopTags;
	private List<ProductCat> productCats;
	// 给页面使用
	private Integer attScorePercent;
	private Integer dishScorePercent;
	private BigDecimal distance;// 当前距离
	private List<ProductInfo> products;
	private String totalprice;// 购物车该商店物品的总价

	public Shop() {
		products = new ArrayList<ProductInfo>();
	}

	/**
	 * 刷新给页面使用的数据
	 */
	public void refresh(BigDecimal lon, BigDecimal lat) {
		if (attitudeScore != null) {
			attScorePercent = attitudeScore.intValue() * 20;
		}
		if (dishScore != null) {
			dishScorePercent = dishScore.intValue() * 20;
		}
		if (poi != null) {
			distance = Location.getDistance(lon, lat, poi.getLongitude(), poi.getLatitude());
		}
		if (productCats != null) {
			Iterator<ProductCat> pcit = productCats.iterator();
			while (pcit.hasNext()) {
				ProductCat pc = pcit.next();
				if (pc.getProducts() != null) {
					Iterator<Product> pit = pc.getProducts().iterator();
					while (pit.hasNext()) {
						Product p = pit.next();
						p.setDishScorePercent(p.getDishScore().intValue() * 20);
						if (p.getDescription() != null && p.getDescription().length() > 20)
							p.setSimpleDisc(p.getDescription().substring(0, 20) + "...");
					}
				}
			}
		}
	}

	public void refresh() {
		if (attitudeScore != null) {
			attScorePercent = attitudeScore.intValue() * 20;
		}
		if (dishScorePercent != null) {
			dishScorePercent = dishScore.intValue() * 20;
		}
		if (productCats != null) {
			Iterator<ProductCat> pcit = productCats.iterator();
			while (pcit.hasNext()) {
				ProductCat pc = pcit.next();
				if (pc.getProducts() != null) {
					Iterator<Product> pit = pc.getProducts().iterator();
					while (pit.hasNext()) {
						Product p = pit.next();
						p.setDishScorePercent(p.getDishScore().intValue() * 20);
					}
				}
			}
		}
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ShopCat getShopcat() {
		return shopcat;
	}

	public void setShopcat(ShopCat shopcat) {
		this.shopcat = shopcat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getIcon() {
		return icon;
	}

	public void setIcon(File icon) {
		this.icon = icon;
	}

	public Poi getPoi() {
		return poi;
	}

	public void setPoi(Poi poi) {
		this.poi = poi;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public DeliveryRule getDeliveryRule() {
		return deliveryRule;
	}

	public void setDeliveryRule(DeliveryRule deliveryRule) {
		this.deliveryRule = deliveryRule;
	}

	public ShopState getShopState() {
		return shopState;
	}

	public void setShopState(ShopState shopState) {
		this.shopState = shopState;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(BigDecimal deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public Integer getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(Integer averageTime) {
		this.averageTime = averageTime;
	}

	public Time getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}

	public Time getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
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

	public Integer getMonthlySale() {
		return monthlySale;
	}

	public void setMonthlySale(Integer monthlySale) {
		this.monthlySale = monthlySale;
	}

	public Timestamp getCreatTime() {
		return createTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.createTime = creatTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<ShopTag> getShopTags() {
		return shopTags;
	}

	public void setShopTags(List<ShopTag> shopTags) {
		this.shopTags = shopTags;
	}

	public Integer getFkShopCatsShopcatid() {
		return fkShopCatsShopcatid;
	}

	public void setFkShopCatsShopcatid(Integer fkShopCatsShopcatid) {
		this.fkShopCatsShopcatid = fkShopCatsShopcatid;
	}

	public Integer getFkUsersUserid() {
		return fkUsersUserid;
	}

	public void setFkUsersUserid(Integer fkUsersUserid) {
		this.fkUsersUserid = fkUsersUserid;
	}

	public Integer getFkFilesIcon() {
		return fkFilesIcon;
	}

	public void setFkFilesIcon(Integer fkFilesIcon) {
		this.fkFilesIcon = fkFilesIcon;
	}

	public String getFkPoisPoiid() {
		return fkPoisPoiid;
	}

	public void setFkPoisPoiid(String fkPoisPoiid) {
		this.fkPoisPoiid = fkPoisPoiid;
	}

	public Integer getFkQualificationsQualificationid() {
		return fkQualificationsQualificationid;
	}

	public void setFkQualificationsQualificationid(Integer fkQualificationsQualificationid) {
		this.fkQualificationsQualificationid = fkQualificationsQualificationid;
	}

	public Integer getFkDeliveryRulesDeliveryRule() {
		return fkDeliveryRulesDeliveryRule;
	}

	public void setFkDeliveryRulesDeliveryRule(Integer fkDeliveryRulesDeliveryRule) {
		this.fkDeliveryRulesDeliveryRule = fkDeliveryRulesDeliveryRule;
	}

	public Integer getFkShopStatesShopState() {
		return fkShopStatesShopState;
	}

	public void setFkShopStatesShopState(Integer fkShopStatesShopState) {
		this.fkShopStatesShopState = fkShopStatesShopState;
	}

	@Override
	public String toString() {
		return "Shop [shopid=" + shopid + ", name=" + name + ", fkShopCatsShopcatid=" + fkShopCatsShopcatid
				+ ", fkUsersUserid=" + fkUsersUserid + ", fkFilesIcon=" + fkFilesIcon + ", fkPoisPoiid=" + fkPoisPoiid
				+ ", fkQualificationsQualificationid=" + fkQualificationsQualificationid
				+ ", fkDeliveryRulesDeliveryRule=" + fkDeliveryRulesDeliveryRule + ", fkShopStatesShopState="
				+ fkShopStatesShopState + ", shopcat=" + shopcat + ", user=" + user + ", icon=" + icon + ", poi=" + poi
				+ ", qualification=" + qualification + ", deliveryRule=" + deliveryRule + ", shopState=" + shopState
				+ ", address=" + address + ", basePrice=" + basePrice + ", deliveryPrice=" + deliveryPrice
				+ ", averageTime=" + averageTime + ", openTime=" + openTime + ", closeTime=" + closeTime + ", phone="
				+ phone + ", announcement=" + announcement + ", attitudeScore=" + attitudeScore + ", dishScore="
				+ dishScore + ", monthlySale=" + monthlySale + ", creatTime=" + createTime + ", flag=" + flag
				+ ", shopTags=" + shopTags + "]";
	}

	public List<ProductCat> getProductCats() {
		return productCats;
	}

	public void setProductCats(List<ProductCat> productCats) {
		this.productCats = productCats;
	}

	public Integer getAttScorePercent() {
		return attScorePercent;
	}

	public void setAttScorePercent(Integer attScorePercent) {
		this.attScorePercent = attScorePercent;
	}

	public Integer getDishScorePercent() {
		return dishScorePercent;
	}

	public void setDishScorePercent(Integer dishScorePercent) {
		this.dishScorePercent = dishScorePercent;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public List<ProductInfo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductInfo> products) {
		this.products = products;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

}
