package com.lion.springcloud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {

	public static List<User> users = new ArrayList<>();

	public static void main(String[] args) {
		UserServiceApplication.init();

		SpringApplication.run(UserServiceApplication.class, args);

	}

	private static void init() {
		for (long i = 0; i < 3; i++) {
			User user = new User();
			user.setId(i);
			user.setUsername("username_" + i);
			user.setPassword("password" + i);

			UserServiceApplication.users.add(user);
		}

	}
}
