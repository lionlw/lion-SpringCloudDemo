package com.lion.springcloud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulUserServiceApplication {

	public static List<User> users = new ArrayList<>();

	public static void main(String[] args) {
		ConsulUserServiceApplication.init();

		SpringApplication.run(ConsulUserServiceApplication.class, args);
	}

	private static void init() {
		for (long i = 0; i < 3; i++) {
			User user = new User();
			user.setId(i);
			user.setUsername("username_" + i);
			user.setPassword("password" + i);

			ConsulUserServiceApplication.users.add(user);
		}

	}
}
