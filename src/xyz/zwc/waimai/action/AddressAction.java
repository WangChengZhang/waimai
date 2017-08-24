package xyz.zwc.waimai.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonMappingException;

import xyz.zwc.amap.entity.PoiParams;
import xyz.zwc.amap.entity.PoiResults;
import xyz.zwc.amap.entity.Pois;
import xyz.zwc.amap.service.AmapService;
import xyz.zwc.waimai.entity.CustAddress;
import xyz.zwc.waimai.entity.Poi;
import xyz.zwc.waimai.entity.User;
import xyz.zwc.waimai.service.CustAddressService;
import xyz.zwc.waimai.service.PoiService;
import xyz.zwc.waimai.util.Location;
import xyz.zwc.waimai.util.Text;

@Controller
@RequestMapping("/address")
public class AddressAction {

	@Resource
	private CustAddressService custAddressService;
	@Resource
	private AmapService amapService;
	@Resource
	private PoiService poiService;

	@RequestMapping("/load")
	public ModelAndView load(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null) {
			List<CustAddress> addresses = custAddressService
					.selectByUserid(((User) session.getAttribute("user")).getUserid());
			modelAndView.addObject("addresses", addresses);
			modelAndView.setViewName("addressload");
		}
		return modelAndView;
	}

	@RequestMapping("/addresssave")
	public ModelAndView save(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null) {
			modelAndView.setViewName("addresssave");
		}
		return modelAndView;
	}

	@RequestMapping("/confirm")
	@ResponseBody
	public String confirm(HttpServletRequest request, HttpSession session, String custaddid, String username,
			String gender, String telephone, String address, String poiid) {
		String result = "{\"result\":\"paramerror\"}";
		if (session.getAttribute("user") != null && username != null && !username.isEmpty() && gender != null
				&& (gender.equals("1") || gender.equals("2")) && telephone != null && Text.isInteger(telephone)
				&& telephone.length() == 11 && address != null && !address.isEmpty() && poiid != null
				&& !poiid.isEmpty()) {
			PoiParams poiParam = new PoiParams();
			poiParam.setKey((String) request.getServletContext().getAttribute("amapserverkey"));
			poiParam.setId(poiid);
			try {
				PoiResults<Pois> poiResult = amapService.getPois(poiParam);
				if (poiResult.getCount() > 0) {
					Pois pois = poiResult.getPois().get(0);
					Poi poi = new Poi();
					poi.setAddress(pois.getAddress());
					poi.setFkAdRegionsAdcode(pois.getAdcode());
					List<BigDecimal> lonlat = Location.convert(pois.getLocation());
					poi.setLongitude(lonlat.get(0));
					poi.setLatitude(lonlat.get(1));
					poi.setName(pois.getName());
					poi.setPoiid(pois.getId());
					CustAddress custAddress = new CustAddress();
					custAddress.setAddress(address);
					custAddress.setFkPoisPoiid(poiid);
					custAddress.setFkUsersUserid(((User) session.getAttribute("user")).getUserid());
					custAddress.setGender(Integer.parseInt(gender));
					custAddress.setName(username);
					custAddress.setPhone(telephone);
					poiService.insert(poi);
					if (custaddid != null && !custaddid.isEmpty() && Text.isInteger(custaddid)) {
						custAddress.setCustaddid(Integer.parseInt(custaddid));
						int flag = custAddressService.update(custAddress);
						if (flag > 0) {
							result = "{\"result\":\"success\"}";
						}
					} else {
						int flag = custAddressService.insert(custAddress);
						if (flag > 0) {
							result = "{\"result\":\"success\"}";
						}
					}
				}
			} catch (Exception e) {
				Log log = LogFactory.getLog(HomeAction.class);
				e.printStackTrace();
				if (e instanceof JsonMappingException) {
					log.error("address页无法获取poi信息");
				} else {
					log.error("address添加页异常", e);
				}
			}
		} else if (poiid == null || poiid.isEmpty()) {
			result = "{\"result\":\"poinull\"}";
		}
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpSession session, String custaddid) {
		String result = "{\"result\":\"failed\"}";
		User user = (User) session.getAttribute("user");
		if (user != null && custaddid != null && !custaddid.isEmpty() && Text.isInteger(custaddid)) {
			CustAddress custadd = new CustAddress();
			custadd.setCustaddid(Integer.parseInt(custaddid));
			CustAddress radd = custAddressService.select(custadd);
			if (radd != null && radd.getFkUsersUserid().equals(user.getUserid())) {
				if (custAddressService.delete(custadd) > 0) {
					result = "{\"result\":\"success\"}";
				}
			}
		}
		return result;
	}
}
