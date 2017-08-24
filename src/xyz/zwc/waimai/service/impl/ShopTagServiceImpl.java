package xyz.zwc.waimai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.ShopTagMapper;
import xyz.zwc.waimai.entity.ShopTag;
import xyz.zwc.waimai.service.ShopTagService;

@Service("shopTagService")
public class ShopTagServiceImpl implements ShopTagService {
	
	@Autowired
	private ShopTagMapper shopTagMapper;

	@Override
	public ShopTag select(ShopTag shopTag) {
		// TODO Auto-generated method stub
		return shopTagMapper.select(shopTag);
	}

	@Override
	public List<ShopTag> selecAll() {
		// TODO Auto-generated method stub
		return shopTagMapper.selectAll();
	}

}
