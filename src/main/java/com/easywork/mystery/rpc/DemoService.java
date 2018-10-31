package com.easywork.mystery.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easywork.mystery.entity.Product;

@FeignClient("mystery-appB")
public interface DemoService {
	@RequestMapping("/mystery/Product/findAllProduct")
	@ResponseBody
	public Product methodA();
}

