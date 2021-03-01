package com.lion.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务降级的处理类（结合hystrix）
 * 
 * @author lion
 *
 * @date 2021年1月15日
 */
@RestController
public class FallbackController {

	@GetMapping("/fallback")
	public Object fallback() {
		Map<String, Object> result = new HashMap<>();
		result.put("data", null);
		result.put("message", "Get request fallback!");
		result.put("code", 500);
		return result;
	}

}