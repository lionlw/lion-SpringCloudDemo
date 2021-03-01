package com.lion.springcloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignConfig {
	@Bean
	public Logger.Level feignLogLevel() {
		return Logger.Level.FULL;
	}
}
