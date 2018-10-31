package com.easywork.mystery.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.easycore.utils.BaseController;
import com.easywork.mystery.entity.Tuser;
import com.easywork.mystery.service.TuserService;

@Controller
@RequestMapping("/mystery/Tuser")
public class TuserController extends BaseController {
	@Autowired
	private TuserService tuserService;
	
	@RequestMapping("/login")
	public String login(String name,String password,HttpServletRequest req) {
		Tuser tuser = tuserService.findByNameAndPassword(name, password);
		if(tuser==null) {
			return "error/500";
		}
		req.getSession().setAttribute("user", tuser);
		return "/demo/view";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		req.getSession().removeAttribute("user");
		return "/demo/login";
	}

}