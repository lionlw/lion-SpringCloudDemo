package com.lion.springcloud;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreLogFilter extends ZuulFilter {

	/**
	 * 过滤器类型，有pre、routing、post、error四种。
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 过滤器执行顺序，数值越小优先级越高。
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 是否进行过滤，返回true会执行过滤。
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 自定义的过滤器逻辑，当shouldFilter()返回true时会执行。
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		String remoteHost = request.getRemoteHost();
		String method = request.getMethod();
		String requestURI = request.getRequestURI();

		System.out.println("Remote host:" + remoteHost + ",method:" + method + ",uri:" + requestURI);
		return null;
	}
}