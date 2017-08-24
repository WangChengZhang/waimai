package xyz.zwc.waimai.service;

import java.math.BigDecimal;
import java.util.List;

import xyz.zwc.waimai.entity.Shop;
import xyz.zwc.waimai.entity.ShopCat;
import xyz.zwc.waimai.entity.ShopTag;

public interface ShopService {
	public List<Shop> selectByCat(ShopCat shopCat, List<BigDecimal> range);

	public List<Shop> selectByTag(ShopTag shopTag, List<BigDecimal> range);

	public List<Shop> selectByCatAndTag(ShopCat shopCat, ShopTag shopTag, List<BigDecimal> range);

	public List<Shop> selectAll(List<BigDecimal> range);

	public Shop selectById(int shopid);

	public Shop selectLast();

	public int insert(Shop shop);

	public int insertShopTag(int shopid, int tagid);
}
