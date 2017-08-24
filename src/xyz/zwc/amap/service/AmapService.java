package xyz.zwc.amap.service;

import xyz.zwc.amap.entity.AdcodeParams;
import xyz.zwc.amap.entity.AdcodeResults;
import xyz.zwc.amap.entity.DistrictLevel1;
import xyz.zwc.amap.entity.IpParams;
import xyz.zwc.amap.entity.IpResults;
import xyz.zwc.amap.entity.PoiParams;
import xyz.zwc.amap.entity.PoiResults;
import xyz.zwc.amap.entity.Pois;

public interface AmapService {
	public AdcodeResults<DistrictLevel1> getAds(AdcodeParams adParam) throws Exception;

	public PoiResults<Pois> getPois(PoiParams poiParam) throws Exception;
	
	public IpResults getIpLocation(IpParams ipParam) throws Exception;

}
