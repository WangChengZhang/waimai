package xyz.zwc.waimai.service;

import java.util.List;

import xyz.zwc.waimai.entity.ShopTag;

public interface ShopTagService {
	public ShopTag select(ShopTag shoTag);
	
	public List<ShopTag> selecAll();
}
