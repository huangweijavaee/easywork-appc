package com.easywork.mystery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easycore.utils.BaseController;
import com.easywork.mystery.entity.Product;
import com.easywork.mystery.rpc.DemoService;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class HomeController extends BaseController {
	@Autowired
	DemoService demoService;
	
	@RequestMapping("/")
	public String view() {
		return "demo/view";
	}

	@RequestMapping("/openWork/{view}")
	public String openWork(@PathVariable("view") String view) {
		return "demo/" + view;
	}
	@RequestMapping("/findAllProduct")
	
	public String findAllProduct() {
		Product product = demoService.methodA();
		System.out.println("==================="+product.getProname());
		ModelAndView mv=new ModelAndView();
		mv.addObject("product", product);
		return "demo/mv";
	}

}