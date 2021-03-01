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
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public Result<User> getUser(@PathVariable Long id) {
		User user = userService.getUser(id);
		System.out.println("根据id获取用户信息，用户名称为：" + user.getUsername());
		return new Result<User>(user);
	}

	@GetMapping("/listUsersByIds")
	public Result<List<User>> listUsersByIds(@RequestParam List<Long> ids) {
		List<User> userList = userService.listUsersByIds(ids);
		System.out.println("根据ids获取用户信息，用户列表为：" + userList);
		return new Result<List<User>>(userList);
	}

	@GetMapping("/getByUsername")
	public Result<User> getByUsername(@RequestParam String username) {
		User user = userService.getByUsername(username);
		System.out.println("根据用户名获取用户信息，用户名称为：" + user.getUsername());
		return new Result<User>(user);
	}

}