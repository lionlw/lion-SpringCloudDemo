package com.lion.springcloud;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserFallbackService implements UserService {

	@Override
	public Result<User> getUser(Long id) {
		return new Result("调用失败，服务被降级", 500);
	}

	@Override
	public Result<List<User>> listUsersByIds(List<Long> ids) {
		return new Result("调用失败，服务被降级", 500);
	}

	@Override
	public Result<User> getByUsername(String username) {
		return new Result("调用失败，服务被降级", 500);
	}

}
