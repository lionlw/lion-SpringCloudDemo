package com.lion.springcloud;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", fallback = UserFallbackService.class)
public interface UserService {

	@GetMapping("/user/{id}")
	Result<User> getUser(@PathVariable Long id);

	@GetMapping("/user/listUsersByIds")
	Result<List<User>> listUsersByIds(@RequestParam List<Long> ids);

	@GetMapping("/user/getByUsername")
	Result<User> getByUsername(@RequestParam String username);

}