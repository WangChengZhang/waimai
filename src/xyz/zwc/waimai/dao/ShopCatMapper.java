package xyz.zwc.waimai.dao;

import java.util.List;

import xyz.zwc.waimai.entity.ShopCat;

public interface ShopCatMapper extends Mapper<ShopCat> {
	public List<ShopCat> selectAll();
}
