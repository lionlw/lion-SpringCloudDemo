package com.lion.springcloud;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public User getUser(Long id) {
		for (User user : UserServiceApplication.users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}

		return null;
	}

	public List<User> listUsersByIds(List<Long> ids) {
		return UserServiceApplication.users;
	}

	public User getByUsername(String username) {
		for (User user : UserServiceApplication.users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}

		return null;
	}
}
