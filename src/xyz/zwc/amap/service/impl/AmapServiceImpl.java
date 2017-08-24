package xyz.zwc.amap.service.impl;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.zwc.amap.entity.AdcodeParams;
import xyz.zwc.amap.entity.AdcodeResults;
import xyz.zwc.amap.entity.DistrictLevel1;
import xyz.zwc.amap.entity.IpParams;
import xyz.zwc.amap.entity.IpResults;
import xyz.zwc.amap.entity.PoiParams;
import xyz.zwc.amap.entity.PoiResults;
import xyz.zwc.amap.entity.Pois;
import xyz.zwc.amap.service.AmapService;

@Service("amapService")
public class AmapServiceImpl implements AmapService {

	@Override
	public AdcodeResults<DistrictLevel1> getAds(AdcodeParams adParam) throws Exception {
		String json;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet("http://restapi.amap.com/v3/config/district?key=" + adParam.getKey()
					+ "&keywords=" + adParam.getKeywords() + "&subdistrict=" + adParam.getSubdistrict() + "&showbiz="
					+ adParam.getShowbiz() + "&extensions=" + adParam.getExtensions());
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				json = EntityUtils.toString(entity);
				EntityUtils.consume(entity);// 关闭流
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		ObjectMapper mapper = new ObjectMapper();// jackson映射器
		// 当反序列化json时，未知属性会引起的反序列化被打断，忽略bean中未定义的属性
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		AdcodeResults<DistrictLevel1> result = mapper.readValue(json,
				new TypeReference<AdcodeResults<DistrictLevel1>>() {
				});// 实体类型为泛型，所以使用typereference

		return result;
	}

	@Override
	public PoiResults<Pois> getPois(PoiParams poiParam) throws Exception {
		String json;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet("http://restapi.amap.com/v3/place/detail?key=" + poiParam.getKey()+"&id="+poiParam.getId());
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				json = EntityUtils.toString(entity);
				EntityUtils.consume(entity);// 关闭流
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		ObjectMapper mapper = new ObjectMapper();// jackson映射器
		// 当反序列化json时，未知属性会引起的反序列化被打断，忽略bean中未定义的属性
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PoiResults<Pois> result = mapper.readValue(json,
				new TypeReference<PoiResults<Pois>>() {
				});// 实体类型为泛型，所以使用typereference

		return result;
	}
	
	@Override
	public IpResults getIpLocation(IpParams ipParam) throws Exception {
		String json;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet("http://restapi.amap.com/v3/ip?key=" + ipParam.getKey()
					+ "&ip=" + ipParam.getIp());
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				json = EntityUtils.toString(entity);
				EntityUtils.consume(entity);// 关闭流
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		ObjectMapper mapper = new ObjectMapper();// jackson映射器
		// 当反序列化json时，未知属性会引起的反序列化被打断，忽略bean中未定义的属性
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		IpResults result = mapper.readValue(json,IpResults.class);

		return result;
	}

	public static void main(String[] args) {
		AmapServiceImpl amap = new AmapServiceImpl();
		/*
		AdcodeParams param = new AdcodeParams();
		param.setKey("fec12fc03fea5c6928b022030cde0c80");
		param.setKeywords("010");
		param.setSubdistrict(0);
		try {
			AdcodeResults<DistrictLevel1> result = amap.getAds(param);
			System.out.print(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		PoiParams param = new PoiParams();
		param.setKey("fec12fc03fea5c6928b022030cde0c80");
		param.setId("B0FFFZ7A7D");
		try {
			System.out.print(amap.getPois(param));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		IpParams param = new IpParams();
		param.setKey("fec12fc03fea5c6928b022030cde0c80");
		param.setIp("183.5.243.59");
		try {
			System.out.print(amap.getIpLocation(param));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
