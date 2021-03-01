package com.lion.springcloud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserFeignController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public Result<User> getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}

	@GetMapping("/listUsersByIds")
	public Result<List<User>> listUsersByIds(@RequestParam List<Long> ids) {
		return userService.listUsersByIds(ids);
	}

	@GetMapping("/getByUsername")
	public Result<User> getByUsername(@RequestParam String username) {
		return userService.getByUsername(username);
	}

}
