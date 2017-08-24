package xyz.zwc.waimai.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonMappingException;

import xyz.zwc.amap.entity.IpParams;
import xyz.zwc.amap.entity.IpResults;
import xyz.zwc.amap.service.AmapService;

@Controller
@RequestMapping(value = {"/home","/",""})
public class HomeAction {

	@Resource
	private AmapService amapService;

	@RequestMapping(value = "",method = {RequestMethod.GET,RequestMethod.POST})
	ModelAndView home(HttpServletRequest request, HttpSession session,Model modle) {
		IpParams ipParam = new IpParams();
		IpResults ipResult = new IpResults();
		ModelAndView modelAndView = new ModelAndView();
		try {
			// 绑定输入参数
			ipParam.setKey((String) request.getServletContext().getAttribute("amapserverkey"));
			ipParam.setIp(request.getRemoteAddr());
			// 调用service
			ipResult = amapService.getIpLocation(ipParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log log = LogFactory.getLog(HomeAction.class);
			if (e instanceof JsonMappingException) {
				log.error("home无法获取该用户ip定位");
			} else {
				log.error("home异常", e);
			}
		}
		// 绑定输出参数
		if (ipResult.getAdcode() != null && ipResult.getCity() != null) {
			modelAndView.addObject("city", ipResult.getCity());
			modelAndView.addObject("adcode", ipResult.getAdcode());
		} else {
			modelAndView.addObject("city", "北京市");
			modelAndView.addObject("adcode", "110000");
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}

}
