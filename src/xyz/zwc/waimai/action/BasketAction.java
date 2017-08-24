package xyz.zwc.waimai.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import xyz.zwc.waimai.entity.Product;
import xyz.zwc.waimai.entity.page.Basket;
import xyz.zwc.waimai.entity.page.ProductInfo;
import xyz.zwc.waimai.service.ProductService;
import xyz.zwc.waimai.util.Text;

@Controller
@RequestMapping("/basket")
public class BasketAction {

	@Resource
	private ProductService productService;

	@RequestMapping("/addproduct")
	@ResponseBody
	public String addProduct(HttpSession session, String productid) {
		if (productid != null) {
			Product product = productService.selectById(Integer.parseInt(productid));
			if (product != null) {
				if (session.getAttribute("sessionBasket") == null) {
					session.setAttribute("sessionBasket", new Basket());
				}
				Basket basket = (Basket) session.getAttribute("sessionBasket");
				if (basket.getProductList() == null) {
					basket.setProductList(new ArrayList<ProductInfo>());
				}
				List<ProductInfo> products = basket.getProductList();
				boolean flag = false;
				Iterator<ProductInfo> iterator = products.iterator();
				while (iterator.hasNext()) {
					ProductInfo productexist = iterator.next();
					if (productexist.getProductid() == product.getProductid()) {
						productexist.setCount(productexist.getCount() + 1);
						flag = true;
					}
				}
				if (flag == false) {
					ProductInfo productInfo = new ProductInfo();
					productInfo.setProductid(product.getProductid());
					productInfo.setName(product.getName());
					productInfo.setCount(1);
					productInfo.setUnitPrice(product.getPrice());
					productInfo.setShop(product.getShop());
					products.add(productInfo);
				}
				basket.refresh();
			}
		}
		return "success";
	}

	@RequestMapping("/load")
	public ModelAndView load(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("sessionBasket") == null) {
			session.setAttribute("sessionBasket", new Basket());
		}
		modelAndView.addObject("basket", session.getAttribute("sessionBasket"));
		modelAndView.setViewName("basket");
		return modelAndView;
	}

	@RequestMapping("/add")
	@ResponseBody
	public String add(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("sessionBasket") != null && request.getParameter("productid") != null) {
			Iterator<ProductInfo> iterator = ((Basket) session.getAttribute("sessionBasket")).getProductList()
					.iterator();
			while (iterator.hasNext()) {
				ProductInfo product = iterator.next();
				if (product.getProductid().toString().equals(request.getParameter("productid"))) {

					product.setCount(product.getCount() + 1);
				}
			}
			// 更新购物车值
			((Basket) session.getAttribute("sessionBasket")).refresh();
		}
		return "success";
	}

	@RequestMapping("/sub")
	@ResponseBody
	public String sub(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("sessionBasket") != null && request.getParameter("productid") != null) {
			Iterator<ProductInfo> iterator = ((Basket) session.getAttribute("sessionBasket")).getProductList()
					.iterator();
			while (iterator.hasNext()) {
				ProductInfo product = iterator.next();
				if (product.getProductid().toString().equals(request.getParameter("productid"))) {

					product.setCount(product.getCount() - 1);
				}
			}
			// 更新购物车值
			((Basket) session.getAttribute("sessionBasket")).refresh();
		}
		return "success";
	}

	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("sessionBasket") != null && request.getParameter("productid") != null
				&& request.getParameter("count") != null && Text.isInteger(request.getParameter("count"))) {
			Iterator<ProductInfo> iterator = ((Basket) session.getAttribute("sessionBasket")).getProductList()
					.iterator();
			while (iterator.hasNext()) {
				ProductInfo product = iterator.next();
				if (product.getProductid().toString().equals(request.getParameter("productid"))) {
					if (request.getParameter("count").length() < 5) {
						product.setCount(Integer.parseInt(request.getParameter("count")));
					} else {
						product.setCount(9999);
					}
				}
			}
			// 更新购物车值
			((Basket) session.getAttribute("sessionBasket")).refresh();
		}
		return "success";
	}

	@RequestMapping("/clear")
	@ResponseBody
	public String clear(HttpSession session) {
		if (session.getAttribute("sessionBasket") != null) {
			session.removeAttribute("sessionBasket");
		}
		return "success";
	}
}
