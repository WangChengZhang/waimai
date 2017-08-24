package xyz.zwc.waimai.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import xyz.zwc.waimai.entity.User;
import xyz.zwc.waimai.service.UserService;
import xyz.zwc.waimai.util.Encode;
import xyz.zwc.waimai.util.Text;

@Controller
@RequestMapping("/login")
public class LoginAction {

	@Resource
	private UserService userService;

	@RequestMapping("")
	public ModelAndView login(HttpSession session, String redirect) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null) {
			// 用户已经登陆重定向到place
			modelAndView.setViewName("redirect:place");
		} else {
			if (redirect != null) {
				modelAndView.addObject("redirect", redirect);
			}
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/verify")
	@ResponseBody
	public String verify(String username, String password, String checkcode, String type, HttpSession session) {
		String result = null;
		// 未登录用户才可登陆
		if (session.getAttribute("user") == null) {
			if (username != null && !username.isEmpty() && checkcode != null && !checkcode.isEmpty() && password != null
					&& !password.isEmpty() && type != null && !type.isEmpty()) {
				if (((new Date()).getTime() - (Long) session.getAttribute("checkCodeTime")) < 60000
						&& ((String) session.getAttribute("checkCode")).equalsIgnoreCase(checkcode)) {
					try {
						User user = userService.login(username, password, type);
						if (user != null) {
							session.setAttribute("user", user);
							result = "{\"result\":\"success\"}";
						} else {
							result = "{\"result\":\"passworderror\"}";
						}
					} catch (NoSuchAlgorithmException e) {
						Log log = LogFactory.getLog(HomeAction.class);
						e.printStackTrace();
						log.error("login密码加密错误", e);
					} catch (UnsupportedEncodingException e) {
						Log log = LogFactory.getLog(HomeAction.class);
						e.printStackTrace();
						log.error("login密码加密错误", e);
					}
				} else {
					result = "{\"result\":\"checkcodeerror\"}";
				}
			} else {
				result = "{\"result\":\"passworderror\"}";
			}
		}
		return result;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
			modelAndView.setViewName("logout");
		} else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@RequestMapping("/register")
	public ModelAndView register(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("user") != null) {
			// 用户已经登陆重定向到place
			modelAndView.setViewName("redirect:place");
		} else {
			modelAndView.setViewName("register");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/register/verify", method = RequestMethod.POST)
	@ResponseBody
	public String registerVer(String telephone, String username, String mail, String password, HttpSession session) {
		String result = "{\"result\":\"0\"}";// 0代表传入参数不足或用户已经登陆1代表用户名重复或格式错误2代表手机号重复或错误3代表邮箱重复或错误4代表密码格式错误5代表注册成功
		if (session.getAttribute("user") == null && telephone != null && !telephone.isEmpty() && password != null
				&& !password.isEmpty()) {
			// 先判断所有参数格式是否正确
			// 手机 长度11
			if (Text.isInteger(telephone) && telephone.length() == 11) {
				// 判断密码
				if (Text.isPassword(password)) {
					// 判断用户名
					if (username == null || username.isEmpty() || username.length() <= 15) {
						// 判断email
						if (mail == null || mail.isEmpty() || Text.isEmail(mail)) {
							User user = new User();
							user.setUqTelephone(telephone);
							user.setFlag(1);
							user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
							user.setFkRolesRoleid(1);
							user.setPoint(0);
							if (username != null) {
								user.setName(username);
							}
							if (mail != null) {
								user.setUqEmail(mail);
							}
							try {
								user.setPassword(Encode.sha256(password));
								int res = userService.insert(user);
								switch (res) {
								case 1:
									result = "{\"result\":\"1\"}";// 用户名重复
									break;
								case 2:
									result = "{\"result\":\"2\"}";// 手机号重复
									break;
								case 3:
									result = "{\"result\":\"3\"}";// 邮箱重复
									break;
								default:
									result = "{\"result\":\"5\"}";// 注册成功
									break;
								}
							} catch (NoSuchAlgorithmException e) {
								Log log = LogFactory.getLog(HomeAction.class);
								e.printStackTrace();
								log.error("register密码加密错误", e);
							} catch (UnsupportedEncodingException e) {
								Log log = LogFactory.getLog(HomeAction.class);
								e.printStackTrace();
								log.error("register密码加密错误", e);
							}
						} else {
							result = "{\"result\":\"3\"}";// email格式错误
						}
					} else {
						result = "{\"result\":\"1\"}";// 用户名格式错误
					}
				} else {
					result = "{\"result\":\"4\"}";// 密码格式错误
				}
			} else {
				result = "{\"result\":\"2\"}";// 手机号格式错误
			}
		}
		return result;
	}

}
