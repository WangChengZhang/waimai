package xyz.zwc.waimai.dao;

import java.util.List;

import xyz.zwc.waimai.entity.ShopTag;

public interface ShopTagMapper extends Mapper<ShopTag> {
	public List<ShopTag> selectAll();
}
