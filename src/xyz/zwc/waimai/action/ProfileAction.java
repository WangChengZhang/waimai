package xyz.zwc.waimai.action;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import xyz.zwc.waimai.entity.CustAddress;
import xyz.zwc.waimai.entity.File;
import xyz.zwc.waimai.entity.Order;
import xyz.zwc.waimai.entity.User;
import xyz.zwc.waimai.entity.page.Navigator;
import xyz.zwc.waimai.service.CommentService;
import xyz.zwc.waimai.service.CustAddressService;
import xyz.zwc.waimai.service.FileService;
import xyz.zwc.waimai.service.OrderService;
import xyz.zwc.waimai.service.UserService;
import xyz.zwc.waimai.util.Encode;
import xyz.zwc.waimai.util.Text;

@Controller
@RequestMapping("/profile")
public class ProfileAction {

	@Resource
	OrderService orderService;
	@Resource
	CommentService commentService;
	@Resource
	private CustAddressService custAddressService;
	@Resource
	private FileService fileService;
	@Resource
	private UserService userService;

	@RequestMapping("/order")
	public ModelAndView order(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null) {
			Navigator navigator = new Navigator();
			User user = (User) session.getAttribute("user");
			navigator.setUser(user);
			navigator.setCurrentPage("order");
			List<Order> orders = orderService.selectByUserid(user.getUserid());
			modelAndView.addObject("orders", orders);
			modelAndView.addObject("navigator", navigator);
			modelAndView.setViewName("profileorder");
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("/comment")
	@ResponseBody
	public String comment(HttpSession session, String orderid, String comment, String attitudescore, String dishscore) {
		String result = null;
		User user = (User) session.getAttribute("user");
		if (user != null) {
			if (orderid != null && comment != null && attitudescore != null && dishscore != null && !orderid.isEmpty()
					&& !comment.isEmpty() && !attitudescore.isEmpty() && !dishscore.isEmpty()) {
				String[] score = { "0", "1", "2", "3", "4", "5" };
				List<String> sl = Arrays.asList(score);
				if (sl.contains(attitudescore) && sl.contains(dishscore) && comment.length() <= 100) {
					Order order = orderService.selectByOrderid(Integer.parseInt(orderid));
					if (order != null && order.getFkUserUserid().equals(user.getUserid())
							&& order.getFkOrderStatesState().equals(1)) {
						if (commentService.addComment(order, comment, new BigDecimal(attitudescore),
								new BigDecimal(dishscore)) > 0) {
							result = "{\"result\":\"success\"}";
						}
					}
				}
			}
		}
		return result;
	}

	@RequestMapping("/address")
	public ModelAndView address(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null) {
			Navigator navigator = new Navigator();
			User user = (User) session.getAttribute("user");
			navigator.setUser(user);
			List<CustAddress> addresses = custAddressService.selectByUserid(user.getUserid());
			modelAndView.addObject("addresses", addresses);
			modelAndView.addObject("navigator", navigator);
			modelAndView.setViewName("profileaddress");
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("")
	public ModelAndView profile(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			Navigator navigator = new Navigator();
			navigator.setUser(user);
			modelAndView.addObject("user", user);
			modelAndView.addObject("navigator", navigator);
			modelAndView.setViewName("profile");
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("/modifyicon")
	@ResponseBody
	public String modifyIcon(HttpSession session, HttpServletRequest request, String image) {
		String result = "{\"result\":\"failed\"}";
		User user = (User) session.getAttribute("user");
		try {
			if (user != null) {
				File file = new File();
				if (user.getFkFilesIcon() != null) {
					fileService.deleteById(user.getFkFilesIcon());
				}
				int fr = fileService.insert(request, 100 * 1024, file);
				System.out.println("文件上传结果代码:" + fr);
				if (fr == 2) {
					user.setFkFilesIcon(file.getFileid());
					if (userService.update(user) > 0) {
						result = "{\"result\":\"success\"}";
					}
				} else if (fr == 1) {
					result = "{\"result\":\"typeerror\"}";
				} else if (fr == 3) {
					result = "{\"result\":\"sizeerror\"}";
				}
			}
		} catch (Exception e) {
			Log log = LogFactory.getLog(HomeAction.class);
			e.printStackTrace();
			log.error("头像上传异常", e);
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/modify")
	@ResponseBody
	public String modify(HttpSession session, String username, String email, String telephone, String password) {
		String result = "{\"result\":\"nochange\"}";
		User user = (User) session.getAttribute("user");
		if (user != null) {
			User copy = new User();
			copy.setCreateTime(user.getCreateTime());
			copy.setFkFilesIcon(user.getFkRolesRoleid());
			copy.setFkRolesRoleid(user.getFkRolesRoleid());
			copy.setFlag(user.getFlag());
			copy.setName(user.getName());
			copy.setPassword(user.getPassword());
			copy.setPoint(user.getPoint());
			copy.setUqEmail(user.getUqEmail());
			copy.setUqTelephone(user.getUqTelephone());
			copy.setUserid(user.getUserid());
			if (username != null && !username.isEmpty() && !username.equals(user.getName())
					&& username.length() <= 15) {
				if (userService.selectByName(username) == null) {
					copy.setName(username);
				} else {
					return result = "{\"result\":\"repeat\"}";
				}
			}
			if (email != null && !email.isEmpty() && Text.isEmail(email) && !email.equals(user.getUqEmail())) {
				if (userService.selectByMail(email) == null) {
					copy.setUqEmail(email);
				} else {
					return result = "{\"result\":\"repeat\"}";
				}
			}
			if (telephone != null && !telephone.isEmpty() && !telephone.equals(user.getUqTelephone())
					&& Text.isInteger(telephone) && telephone.length() == 11) {
				if (userService.selectByPhone(telephone) == null) {
					copy.setUqTelephone(telephone);
				} else {
					return result = "{\"result\":\"repeat\"}";
				}
			}
			if (password != null && !password.isEmpty()) {
				if (Text.isPassword(password)) {
					try {
						copy.setPassword(Encode.sha256(password));
					} catch (Exception e) {
						Log log = LogFactory.getLog(HomeAction.class);
						e.printStackTrace();
						log.error("密码修改异常", e);
						return result = "{\"result\":\"passworderror\"}";
					}
				} else {
					return result = "{\"result\":\"passworderror\"}";
				}
			}

			if (userService.update(copy) > 0) {
				session.setAttribute("user", copy);
				return result = "{\"result\":\"success\"}";
			}
		}
		return result;
	}

}
