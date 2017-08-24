package xyz.zwc.waimai.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xyz.zwc.waimai.entity.CustAddress;
import xyz.zwc.waimai.entity.Order;
import xyz.zwc.waimai.entity.OrderItem;
import xyz.zwc.waimai.entity.Shop;
import xyz.zwc.waimai.entity.User;
import xyz.zwc.waimai.entity.page.Basket;
import xyz.zwc.waimai.entity.page.Navigator;
import xyz.zwc.waimai.entity.page.ProductInfo;
import xyz.zwc.waimai.service.CustAddressService;
import xyz.zwc.waimai.service.OrderService;
import xyz.zwc.waimai.service.ProductService;

@Controller
@RequestMapping("checkout")
public class CheckoutAction {

	@Resource
	ProductService productService;
	@Resource
	OrderService orderService;
	@Resource
	CustAddressService custAddressService;

	@RequestMapping("")
	public ModelAndView checkout(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null) {
			Navigator navigator = new Navigator();
			navigator.setUser((User) session.getAttribute("user"));
			if (session.getAttribute("sessionBasket") == null) {
				session.setAttribute("sessionBasket", new Basket());
			}
			modelAndView.addObject("basket", session.getAttribute("sessionBasket"));
			modelAndView.addObject("navigator", navigator);
			modelAndView.setViewName("checkout");
		} else {
			modelAndView.setViewName("redirect:login");
		}
		return modelAndView;
	}

	@RequestMapping("/load")
	public ModelAndView load(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null && session.getAttribute("sessionBasket") != null) {
			List<Shop> shops = new ArrayList<Shop>();
			Basket basket = (Basket) session.getAttribute("sessionBasket");
			Iterator<ProductInfo> it = basket.getProductList().iterator();
			while (it.hasNext()) {
				ProductInfo product = it.next();
				Iterator<Shop> shopit = shops.iterator();
				boolean flag = false;
				while (shopit.hasNext()) {
					Shop shop = shopit.next();
					if (shop.getShopid() == product.getShop().getShopid()) {
						shop.getProducts().add(product);
						shop.setTotalprice((new BigDecimal(shop.getTotalprice()))
								.add(new BigDecimal(product.getPrice())).toString());
						flag = true;
					}
				}
				if (flag == false) {
					Shop newshop = new Shop();
					newshop.setShopid(product.getShop().getShopid());
					newshop.setName(product.getShop().getName());
					newshop.setDeliveryPrice(product.getShop().getDeliveryPrice());
					newshop.getProducts().add(product);
					newshop.setTotalprice(
							(new BigDecimal(product.getPrice())).add(newshop.getDeliveryPrice()).toString());
					shops.add(newshop);
				}
			}
			session.setAttribute("orderProducts", shops);
			modelAndView.addObject("shops", shops);
			modelAndView.setViewName("checkoutload");
		}
		return modelAndView;
	}

	@RequestMapping("/orderconfirm")
	public ModelAndView orderConfirm(HttpSession session, String shopid, String custaddid) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null) {
			Navigator navigator = new Navigator();
			String result = "fail";
			Order order = new Order();
			Shop shop = null;
			navigator.setUser((User) session.getAttribute("user"));
			if (session.getAttribute("orderProducts") != null && shopid != null) {
				@SuppressWarnings("unchecked")
				Iterator<Shop> shopit = ((ArrayList<Shop>) session.getAttribute("orderProducts")).iterator();
				while (shopit.hasNext()) {
					Shop singleShop = shopit.next();
					if (singleShop.getShopid().toString().equals(shopid)) {
						shop = singleShop;
						shopit.remove();
					}
				}
			}

			if (shop != null) {
				order.setOrderItems(new ArrayList<OrderItem>());
				order.setAcPayment(new BigDecimal(shop.getTotalprice()));
				order.setTotalPrice(order.getAcPayment());
				order.setCreateTime(new Timestamp(System.currentTimeMillis()));
				order.setFkDeliveryRulesDeliveryRule(1);
				order.setFkOrderStatesState(1);
				order.setFkShopsShopid(shop.getShopid());
				order.setFkUserUserid(((User) session.getAttribute("user")).getUserid());
				order.setShopname(shop.getName());
				Iterator<ProductInfo> productit = shop.getProducts().iterator();
				while (productit.hasNext()) {
					ProductInfo product = productit.next();
					OrderItem orderItem = new OrderItem();
					orderItem.setFkProductsProductid(product.getProductid());
					orderItem.setNumber(product.getCount());
					order.getOrderItems().add(orderItem);
					if (session.getAttribute("sessionBasket") != null) {
						Basket basket = (Basket) session.getAttribute("sessionBasket");
						Iterator<ProductInfo> basketit = basket.getProductList().iterator();
						while (basketit.hasNext()) {
							if (basketit.next().getProductid() == product.getProductid()) {
								basketit.remove();
							}
						}
						basket.refresh();
					}
				}

				CustAddress custAddress = null;
				if (custaddid != null) {
					CustAddress custadd = new CustAddress();
					custadd.setCustaddid(Integer.parseInt(custaddid));
					custAddress = custAddressService.select(custadd);
				}
				if (custAddress != null) {
					order.setAddress(custAddress.getPoi().getAddress() + "," + custAddress.getPoi().getName() + ","
							+ custAddress.getAddress());
					order.setCustname(custAddress.getName());
					order.setFkCustAddressCustaddid(custAddress.getCustaddid());
					order.setPhone(custAddress.getPhone());
					if (orderService.insert(order) > 0) {
						result = "success";
					}

				} else {
					result = "addressnull";
				}
			} else {
				result = "shopnull";
			}

			modelAndView.addObject("navigator", navigator);
			modelAndView.addObject("result", result);
			modelAndView.setViewName("orderresult");
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

}
