package xyz.zwc.waimai.service;

import java.util.List;

import xyz.zwc.waimai.entity.ShopCat;

public interface ShopCatService {
	public ShopCat select(ShopCat shopCat);
	
	public List<ShopCat> selectAll();
}
