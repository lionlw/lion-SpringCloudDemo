package com.lion.springcloud;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomBlockHandler {

	public static Result handleException1(BlockException exception) {
		return new Result("自定义限流信息", 200);
	}

}
