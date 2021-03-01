package com.lion.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 从Consul配置中心中获取配置信息
 * 
 * @author lion
 *
 * @date 2021年1月14日
 */
@RefreshScope
@RestController
public class ConfigClientController {

	@Value("${config.info}")
	private String configInfo;

	@GetMapping("/configInfo")
	public String getConfigInfo() {
		return configInfo;
	}

}
