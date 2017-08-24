package xyz.zwc.waimai.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.ShopMapper;
import xyz.zwc.waimai.entity.Shop;
import xyz.zwc.waimai.entity.ShopCat;
import xyz.zwc.waimai.entity.ShopTag;
import xyz.zwc.waimai.service.ShopService;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper shopMapper;

	@Override
	public List<Shop> selectByCat(ShopCat shopCat, List<BigDecimal> range) {
		// TODO Auto-generated method stub
		return shopMapper.selectByCat(shopCat, range.get(0), range.get(1), range.get(2), range.get(3));
	}

	@Override
	public List<Shop> selectByTag(ShopTag shopTag, List<BigDecimal> range) {
		// TODO Auto-generated method stub
		return shopMapper.selectByTag(shopTag, range.get(0), range.get(1), range.get(2), range.get(3));
	}

	@Override
	public List<Shop> selectByCatAndTag(ShopCat shopCat, ShopTag shopTag, List<BigDecimal> range) {
		// TODO Auto-generated method stub
		return shopMapper.selectByCatAndTag(shopCat, shopTag, range.get(0), range.get(1), range.get(2), range.get(3));
	}

	@Override
	public List<Shop> selectAll(List<BigDecimal> range) {
		// TODO Auto-generated method stub
		return shopMapper.selectAll(range.get(0), range.get(1), range.get(2), range.get(3));
	}

	@Override
	public Shop selectById(int shopid) {
		// TODO Auto-generated method stub
		return shopMapper.selectById(shopid);
	}

	@Override
	public Shop selectLast() {
		// TODO Auto-generated method stub
		return shopMapper.selectLast();
	}

	@Override
	public int insert(Shop shop) {
		// TODO Auto-generated method stub
		return shopMapper.insert(shop);
	}

	@Override
	public int insertShopTag(int shopid, int tagid) {
		// TODO Auto-generated method stub
		return shopMapper.insertShopTag(shopid, tagid);
	}

}
