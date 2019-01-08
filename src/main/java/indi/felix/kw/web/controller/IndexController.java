package indi.felix.kw.web.controller;


import indi.felix.kw.common.toolkit.Constant;
import indi.felix.kw.core.dto.ResultDto;
import indi.felix.kw.core.model.KUser;
import indi.felix.kw.web.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/index/")
public class IndexController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("isAdmin.shtml")
	@ResponseBody
	public String isAdmin(HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		if (null != kUser && userService.isAdmin(kUser.getuId())){
			return ResultDto.success(true);
		}
		return ResultDto.success(false);
	}
	
	@RequestMapping("login.shtml")
	public String login(KUser kUser, RedirectAttributes attr, HttpServletRequest request){
		if (null != kUser && StringUtils.isNotBlank(kUser.getuAccount()) && 
				StringUtils.isNotBlank(kUser.getuPassword())){
			KUser u = userService.login(kUser);
			if (null != u){
				request.getSession().setAttribute(Constant.SESSION_ID, u);
				request.getSession().setAttribute("username", u.getuNickname());
				return "redirect:/view/indexUI.shtml";
			}
			attr.addFlashAttribute("errorMsg", "账号或密码错误");
			return "redirect:/view/loginUI.shtml";
		}
		attr.addFlashAttribute("errorMsg", "账号或密码不能为空");
		return "redirect:/view/loginUI.shtml";
	}
	
	@RequestMapping("logout.shtml")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.SESSION_ID);
		return "redirect:/view/loginUI.shtml";
	}
}