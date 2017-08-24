package xyz.zwc.waimai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.PoiMapper;
import xyz.zwc.waimai.entity.Poi;
import xyz.zwc.waimai.service.PoiService;

@Service("poiService")
public class PoiServiceImpl implements PoiService {
	@Autowired
	private PoiMapper poiMapper;

	@Override
	public int insert(Poi poi) {
		if (this.select(poi) == null) {
			return poiMapper.insert(poi);
		} else {
			return 0;
		}
	}

	@Override
	public Poi select(Poi poi) {
		// TODO Auto-generated method stub
		return poiMapper.select(poi);
	}

}
