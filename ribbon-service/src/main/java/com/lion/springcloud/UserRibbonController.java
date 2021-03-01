package com.lion.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserRibbonController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${service-url.user-service}")
	private String userServiceUrl;

	@GetMapping("/{id}")
	public Result getUser(@PathVariable Long id) {
		return restTemplate.getForObject(userServiceUrl + "/user/{1}", Result.class, id);
	}

	@GetMapping("/getByUsername")
	public Result getByUsername(@RequestParam String username) {
		return restTemplate.getForObject(userServiceUrl + "/user/getByUsername?username={1}", Result.class, username);
	}

	@GetMapping("/getEntityByUsername")
	public Result getEntityByUsername(@RequestParam String username) {
		ResponseEntity<Result> entity = restTemplate.getForEntity(userServiceUrl + "/user/getByUsername?username={1}", Result.class, username);
		if (entity.getStatusCode().is2xxSuccessful()) {
			return entity.getBody();
		} else {
			return new Result("操作失败", 500);
		}
	}

}