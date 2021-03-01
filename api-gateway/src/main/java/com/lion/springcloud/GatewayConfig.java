package com.lion.springcloud;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 采用代码方式进行路由配置
 * 
 * @author lion
 *
 * @date 2021年1月15日
 */
@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("path_route2", r -> r.path("/user/getByUsername")
						.uri("http://localhost:8101/user/getByUsername"))
				.build();
	}

}
