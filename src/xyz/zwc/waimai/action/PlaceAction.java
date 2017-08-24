package xyz.zwc.waimai.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonMappingException;

import xyz.zwc.amap.entity.PoiParams;
import xyz.zwc.amap.entity.PoiResults;
import xyz.zwc.amap.entity.Pois;
import xyz.zwc.amap.service.AmapService;
import xyz.zwc.waimai.entity.Shop;
import xyz.zwc.waimai.entity.ShopCat;
import xyz.zwc.waimai.entity.ShopTag;
import xyz.zwc.waimai.entity.User;
import xyz.zwc.waimai.entity.page.Navigator;
import xyz.zwc.waimai.entity.page.ShopList;
import xyz.zwc.waimai.service.ShopCatService;
import xyz.zwc.waimai.service.ShopService;
import xyz.zwc.waimai.service.ShopTagService;
import xyz.zwc.waimai.util.Location;

@Controller
@RequestMapping("/place")
public class PlaceAction {

	@Resource
	private AmapService amapService;
	@Resource
	private ShopCatService shopCatService;
	@Resource
	private ShopTagService shopTagService;
	@Resource
	private ShopService shopService;

	@SuppressWarnings("unchecked")
	@RequestMapping("")
	public ModelAndView place(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		// 设置当前poi
		if (request.getParameter("poiid") != null) {
			// 查询传入的poiid是否存在，存在则查询poi对象加入session
			PoiParams poiParam = new PoiParams();
			poiParam.setKey((String) request.getServletContext().getAttribute("amapserverkey"));
			poiParam.setId(request.getParameter("poiid"));
			try {
				PoiResults<Pois> poiResult = amapService.getPois(poiParam);
				if (poiResult.getCount() > 0) {
					session.setAttribute("currentPoi", poiResult.getPois().get(0));
					// 在session传入pois列表，最多存5个
					if (session.getAttribute("pois") == null) {
						List<Pois> pois = new LinkedList<Pois>();
						pois.add(poiResult.getPois().get(0));
						session.setAttribute("pois", pois);
					} else {
						List<Pois> pois = (List<Pois>) session.getAttribute("pois");
						// 不能重复
						Iterator<Pois> iterator = pois.iterator();
						boolean flag = true;
						while (iterator.hasNext()) {
							if (iterator.next().getId().equals(poiResult.getPois().get(0).getId())) {
								flag = false;// 说明有重复
							}
						}
						if (flag) {
							if (pois.size() < 5) {
								pois.add(poiResult.getPois().get(0));
							} else {
								pois.remove(0);
								pois.add(poiResult.getPois().get(0));
							}
							session.setAttribute("pois", pois);
						}
					}
				}
			} catch (Exception e) {
				Log log = LogFactory.getLog(HomeAction.class);
				e.printStackTrace();
				if (e instanceof JsonMappingException) {
					log.error("place无法获取poi信息");
				} else {
					log.error("place页异常", e);
				}
			}
		}
		// 加载place页面
		if (session.getAttribute("currentPoi") != null) {
			xyz.zwc.waimai.entity.page.Place place = new xyz.zwc.waimai.entity.page.Place();
			Navigator navigator = new Navigator();

			// 设置导航栏参数
			navigator.setCurrentPage("place");
			if (session.getAttribute("user") != null) {
				navigator.setUser((User) session.getAttribute("user"));
			}
			// 设置完毕
			// 设置place页面参数
			List<Shop> shops = null;
			List<BigDecimal> range;// 经纬度边界
			List<BigDecimal> currentLocation = Location
					.convert(((Pois) session.getAttribute("currentPoi")).getLocation());// 当前经纬度
			range = Location.getRange(currentLocation.get(0), currentLocation.get(1));

			place.setShopCatList(shopCatService.selectAll());
			place.setShopTagList(shopTagService.selecAll());
			if (request.getParameter("cat") != null && !request.getParameter("cat").isEmpty()) {
				place.setCurrentShopCat(new ShopCat(Integer.parseInt(request.getParameter("cat")), ""));
			}
			if (request.getParameter("tag") != null && !request.getParameter("tag").isEmpty()) {
				place.setCurrentShopTag(new ShopTag(Integer.parseInt(request.getParameter("tag")), ""));
			}
			if (place.getCurrentShopCat() != null && place.getCurrentShopTag() == null) {
				shops = shopService.selectByCat(place.getCurrentShopCat(), range);
			} else if (place.getCurrentShopCat() == null && place.getCurrentShopTag() != null) {
				shops = shopService.selectByTag(place.getCurrentShopTag(), range);
			} else if (place.getCurrentShopCat() != null && place.getCurrentShopTag() != null) {
				shops = shopService.selectByCatAndTag(place.getCurrentShopCat(), place.getCurrentShopTag(), range);
			} else {
				shops = shopService.selectAll(range);
			}

			if (shops != null) {
				place.setShopList((new ArrayList<ShopList>()));
				Iterator<Shop> iterator = shops.iterator();
				while (iterator.hasNext()) {
					Shop shop = iterator.next();
					ShopList shopList = new ShopList();
					shopList.setShop(shop);
					shopList.setDistance(Location.getDistance(currentLocation.get(0), currentLocation.get(1),
							shop.getPoi().getLongitude(), shop.getPoi().getLatitude()));
					shopList.setScorePercent(shop.getDishScore().intValue() * 20);
					place.getShopList().add(shopList);
				}
			}
			// 设置完毕
			modelAndView.addObject("place", place);
			modelAndView.addObject("navigator", navigator);
			modelAndView.setViewName("place");
		} else {
			// 没有传入poi重定向到主页
			modelAndView.setViewName("redirect:home");
		}
		return modelAndView;
	}
}
