package xyz.zwc.waimai.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xyz.zwc.waimai.service.CommentService;

@Controller
@RequestMapping("comment")
public class CommentAction {
	
	@Resource
	private CommentService commentService;

	@RequestMapping("")
	public ModelAndView comment(String shopid, String page) {
		ModelAndView modelAndView = new ModelAndView();

		return modelAndView;
	}
}
