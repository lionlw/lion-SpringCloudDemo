package com.lion.springcloud;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${service-url.user-service}")
	private String userServiceUrl;

	@HystrixCommand(fallbackMethod = "fallbackMethod1")
	public Result getUser(Long id) {
		return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
	}

	/**
	 * 声明的参数需要包含controller的声明参数
	 *
	 * @param id
	 * @return
	 */
	public Result fallbackMethod1(@PathVariable Long id) {
		return new Result("服务调用失败", 500);
	}

	@HystrixCommand(fallbackMethod = "fallbackMethod2", ignoreExceptions = { NullPointerException.class })
	public Result getUserException(Long id) {
		if (id == 1) {
			throw new IndexOutOfBoundsException();
		} else if (id == 2) {
			throw new NullPointerException();
		}

		return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
	}

	public Result fallbackMethod2(@PathVariable Long id, Throwable e) {
		System.out.println("id " + id + ",throwable class:" + e.getClass());
		return new Result("服务调用失败", 500);
	}

	@HystrixCommand(fallbackMethod = "fallbackMethod1", commandKey = "getUserCommand", groupKey = "getUserGroup", threadPoolKey = "getUserThreadPool")
	public Result getUserCommand(Long id) {
		return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
	}

	@CacheResult(cacheKeyMethod = "getCacheKey")
	@HystrixCommand(fallbackMethod = "fallbackMethod1", commandKey = "getUserCache")
	public Result getUserCache(Long id) {
		System.out.println("getUserCache id:" + id);
		return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
	}

	/**
	 * 为缓存生成key的方法
	 *
	 * @return
	 */
	public String getCacheKey(Long id) {
		return String.valueOf(id);
	}

	@HystrixCommand
	@CacheRemove(commandKey = "getUserCache", cacheKeyMethod = "getCacheKey")
	public Result removeCache(Long id) {
		System.out.println("removeCache id:" + id);
		// return restTemplate.postForObject(userServiceUrl + "/user/delete/{1}", null,
		// Result.class, id);
		return new Result<>("操作成功", 0);
	}

	@HystrixCollapser(batchMethod = "listUsersByIds", collapserProperties = {
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
	})
	public Future<User> getUserFuture(Long id) {
		return new AsyncResult<User>() {
			@Override
			public User invoke() {
				Result result = restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
				Map data = (Map) result.getData();
				User user = new User();
				user.setId((Long) data.get("id"));
				user.setUsername((String) data.get("username"));
				user.setPassword((String) data.get("password"));

				System.out.println("getUserById username:" + user.getUsername());
				return user;
			}
		};
	}

	@HystrixCommand
	public List<User> listUsersByIds(List<Long> ids) {
		System.out.println("listUsersByIds:" + ids);
		Result result = restTemplate.getForObject(userServiceUrl + "/user/listUsersByIds?ids={1}", Result.class, StringUtils.join(ids.toArray(), ","));
		return (List<User>) result.getData();
	}

}
