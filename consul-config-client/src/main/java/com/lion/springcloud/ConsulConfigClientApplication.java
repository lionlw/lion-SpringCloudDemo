package com.lion.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulConfigClientApplication.class, args);
	}

}
