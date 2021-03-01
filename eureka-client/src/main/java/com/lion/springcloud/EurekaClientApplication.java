package com.lion.springcloud;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(EurekaClientApplication.class, args);

		// 模拟服务启动，防止程序退出
		(new CountDownLatch(1)).await();
	}

}