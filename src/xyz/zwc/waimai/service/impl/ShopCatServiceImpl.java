package xyz.zwc.waimai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.ShopCatMapper;
import xyz.zwc.waimai.entity.ShopCat;
import xyz.zwc.waimai.service.ShopCatService;

@Service("shopCatService")
public class ShopCatServiceImpl implements ShopCatService {
	
	@Autowired
	private ShopCatMapper shopCatMapper;

	@Override
	public ShopCat select(ShopCat shopCat) {
		// TODO Auto-generated method stub
		return shopCatMapper.select(shopCat);
	}

	@Override
	public List<ShopCat> selectAll() {
		// TODO Auto-generated method stub
		return shopCatMapper.selectAll();
	}

}
