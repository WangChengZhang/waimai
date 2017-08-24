package xyz.zwc.waimai.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.zwc.waimai.entity.Shop;
import xyz.zwc.waimai.entity.ShopCat;
import xyz.zwc.waimai.entity.ShopTag;

public interface ShopMapper extends Mapper<Shop> {

	public List<Shop> selectByCat(@Param("shopCat") ShopCat shopCat, @Param("lon1") BigDecimal lon1,
			@Param("lon2") BigDecimal lon2, @Param("lat1") BigDecimal lat1, @Param("lat2") BigDecimal lat2/* 经纬度边界 */);

	public List<Shop> selectByTag(@Param("shopTag") ShopTag shopTag, @Param("lon1") BigDecimal lon1,
			@Param("lon2") BigDecimal lon2, @Param("lat1") BigDecimal lat1, @Param("lat2") BigDecimal lat2/* 经纬度边界 */);

	public List<Shop> selectByCatAndTag(@Param("shopCat") ShopCat shopCat, @Param("shopTag") ShopTag shopTag,
			@Param("lon1") BigDecimal lon1, @Param("lon2") BigDecimal lon2, @Param("lat1") BigDecimal lat1,
			@Param("lat2") BigDecimal lat2/* 经纬度边界 */);

	public List<Shop> selectAll(@Param("lon1") BigDecimal lon1, @Param("lon2") BigDecimal lon2,
			@Param("lat1") BigDecimal lat1, @Param("lat2") BigDecimal lat2/* 经纬度边界 */);

	public Shop selectById(@Param("shopid") int shopid);

	public int updateScore(@Param("attitudescore") BigDecimal attitudescore, @Param("dishscore") BigDecimal dishscore,
			@Param("shopid") int shopid);

	public Shop selectLast();

	public int insertShopTag(@Param("shopid") int shopid, @Param("tagid") int tagid);
}
