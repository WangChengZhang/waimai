package xyz.zwc.waimai.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xyz.zwc.amap.entity.Pois;
import xyz.zwc.waimai.entity.Comment;
import xyz.zwc.waimai.entity.Page;
import xyz.zwc.waimai.entity.Product;
import xyz.zwc.waimai.entity.ProductCat;
import xyz.zwc.waimai.entity.Shop;
import xyz.zwc.waimai.entity.User;
import xyz.zwc.waimai.entity.page.Navigator;
import xyz.zwc.waimai.service.CommentService;
import xyz.zwc.waimai.service.ProductService;
import xyz.zwc.waimai.service.ShopService;
import xyz.zwc.waimai.util.Location;
import xyz.zwc.waimai.util.Text;

@Controller
@RequestMapping("/shop")
public class ShopAction {

	@Resource
	private ShopService shopService;
	@Resource
	private ProductService productService;
	@Resource
	private CommentService commentService;

	@RequestMapping("")
	public ModelAndView shop(HttpSession session, String shopid) {
		ModelAndView modelAndView = new ModelAndView();
		if (shopid != null && !shopid.isEmpty() && Text.isInteger(shopid)) {
			Shop shop = shopService.selectById(Integer.parseInt(shopid));
			if (shop != null) {
				Navigator navigator = new Navigator();
				// 设置导航栏参数
				if (session.getAttribute("user") != null) {
					navigator.setUser((User) session.getAttribute("user"));
				}
				// 设置shop参数
				if (session.getAttribute("currentPoi") != null) {
					List<BigDecimal> currentLocation = Location
							.convert(((Pois) session.getAttribute("currentPoi")).getLocation());// 当前经纬度
					shop.refresh(currentLocation.get(0), currentLocation.get(1));
				} else {
					shop.refresh();
				}
				modelAndView.addObject("navigator", navigator);
				modelAndView.addObject("shop", shop);
				modelAndView.setViewName("shop");
			} else {
				modelAndView.setViewName("redirect:place");
			}
		} else {
			modelAndView.setViewName("redirect:place");
		}
		return modelAndView;
	}

	@RequestMapping("bysales")
	public ModelAndView bySales(HttpSession session, String shopid) {
		ModelAndView modelAndView = new ModelAndView();
		if (shopid != null && !shopid.isEmpty() && Text.isInteger(shopid)) {
			Shop shop = shopService.selectById(Integer.parseInt(shopid));
			List<Product> products = productService.selectBySales(Integer.parseInt(shopid));
			if (shop != null) {
				Navigator navigator = new Navigator();
				// 设置导航栏参数
				if (session.getAttribute("user") != null) {
					navigator.setUser((User) session.getAttribute("user"));
				}
				// 设置shop参数
				ProductCat productCat = new ProductCat();
				List<ProductCat> productCats = new ArrayList<ProductCat>();
				productCat.setProducts(products);
				productCats.add(productCat);
				shop.setProductCats(productCats);
				if (session.getAttribute("currentPoi") != null) {
					List<BigDecimal> currentLocation = Location
							.convert(((Pois) session.getAttribute("currentPoi")).getLocation());// 当前经纬度
					shop.refresh(currentLocation.get(0), currentLocation.get(1));
				} else {
					shop.refresh();
				}
				modelAndView.addObject("navigator", navigator);
				modelAndView.addObject("shop", shop);
				modelAndView.setViewName("shopbysales");
			} else {
				modelAndView.setViewName("redirect:place");
			}
		} else {
			modelAndView.setViewName("redirect:place");
		}
		return modelAndView;
	}

	@RequestMapping("byscore")
	public ModelAndView byScore(HttpSession session, String shopid) {
		ModelAndView modelAndView = new ModelAndView();
		if (shopid != null && !shopid.isEmpty() && Text.isInteger(shopid)) {
			Shop shop = shopService.selectById(Integer.parseInt(shopid));
			List<Product> products = productService.selectByScore(Integer.parseInt(shopid));
			if (shop != null) {
				Navigator navigator = new Navigator();
				// 设置导航栏参数
				if (session.getAttribute("user") != null) {
					navigator.setUser((User) session.getAttribute("user"));
				}
				// 设置shop参数
				ProductCat productCat = new ProductCat();
				List<ProductCat> productCats = new ArrayList<ProductCat>();
				productCat.setProducts(products);
				productCats.add(productCat);
				shop.setProductCats(productCats);
				if (session.getAttribute("currentPoi") != null) {
					List<BigDecimal> currentLocation = Location
							.convert(((Pois) session.getAttribute("currentPoi")).getLocation());// 当前经纬度
					shop.refresh(currentLocation.get(0), currentLocation.get(1));
				} else {
					shop.refresh();
				}
				modelAndView.addObject("navigator", navigator);
				modelAndView.addObject("shop", shop);
				modelAndView.setViewName("shopbyscore");
			} else {
				modelAndView.setViewName("redirect:place");
			}
		} else {
			modelAndView.setViewName("redirect:place");
		}
		return modelAndView;
	}

	@RequestMapping("byprice")
	public ModelAndView byPrice(HttpSession session, String shopid) {
		ModelAndView modelAndView = new ModelAndView();
		if (shopid != null && !shopid.isEmpty() && Text.isInteger(shopid)) {
			Shop shop = shopService.selectById(Integer.parseInt(shopid));
			List<Product> products = productService.selectByPrice(Integer.parseInt(shopid));
			if (shop != null) {
				Navigator navigator = new Navigator();
				// 设置导航栏参数
				if (session.getAttribute("user") != null) {
					navigator.setUser((User) session.getAttribute("user"));
				}
				// 设置shop参数
				ProductCat productCat = new ProductCat();
				List<ProductCat> productCats = new ArrayList<ProductCat>();
				productCat.setProducts(products);
				productCats.add(productCat);
				shop.setProductCats(productCats);
				if (session.getAttribute("currentPoi") != null) {
					List<BigDecimal> currentLocation = Location
							.convert(((Pois) session.getAttribute("currentPoi")).getLocation());// 当前经纬度
					shop.refresh(currentLocation.get(0), currentLocation.get(1));
				} else {
					shop.refresh();
				}
				modelAndView.addObject("navigator", navigator);
				modelAndView.addObject("shop", shop);
				modelAndView.setViewName("shopbyprice");
			} else {
				modelAndView.setViewName("redirect:place");
			}
		} else {
			modelAndView.setViewName("redirect:place");
		}
		return modelAndView;
	}

	@RequestMapping("/comment")
	public ModelAndView comment(HttpSession session, String shopid, String page) {
		ModelAndView modelAndView = new ModelAndView();
		if (shopid != null && !shopid.isEmpty() && Text.isInteger(shopid)) {
			Shop shop = shopService.selectById(Integer.parseInt(shopid));
			Page<Comment> comments = new Page<Comment>();
			commentService.selectCount(comments, Integer.parseInt(shopid));
			comments.refresh();
			if (shop != null && (page == null
					|| (Integer.parseInt(page) > 0 && Integer.parseInt(page) <= comments.getTotalpages()))) {
				Navigator navigator = new Navigator();
				// 设置导航栏参数
				if (session.getAttribute("user") != null) {
					navigator.setUser((User) session.getAttribute("user"));
				}
				// 设置shop参数和comments参数
				if (page != null && !page.isEmpty()) {
					comments.setCurrentPage(Integer.parseInt(page));
				} else {
					comments.setCurrentPage(1);
				}
				comments.setPageSize(10);
				comments.refresh();
				comments = commentService.selectPage(comments, Integer.parseInt(shopid));
				if (session.getAttribute("currentPoi") != null) {
					List<BigDecimal> currentLocation = Location
							.convert(((Pois) session.getAttribute("currentPoi")).getLocation());// 当前经纬度
					shop.refresh(currentLocation.get(0), currentLocation.get(1));
				} else {
					shop.refresh();
				}
				modelAndView.addObject("navigator", navigator);
				modelAndView.addObject("shop", shop);
				modelAndView.addObject("comments", comments);
				modelAndView.setViewName("shopcomment");
			} else {
				modelAndView.setViewName("redirect:place");
			}
		} else {
			modelAndView.setViewName("redirect:place");
		}
		return modelAndView;
	}
}
