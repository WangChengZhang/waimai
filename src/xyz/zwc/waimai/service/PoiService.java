package xyz.zwc.waimai.service;

import xyz.zwc.waimai.entity.Poi;

public interface PoiService {
	public int insert(Poi poi);
	
	public Poi select(Poi poi);
}
