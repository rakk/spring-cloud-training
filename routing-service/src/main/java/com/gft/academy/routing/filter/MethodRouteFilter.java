package com.gft.academy.routing.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class MethodRouteFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(MethodRouteFilter.class);
	
	@Override
	public Object run() {
		RequestContext currentContext = RequestContext.getCurrentContext();
		
		log.info("Old route: {}", currentContext.getRequest().getRequestURI());
		currentContext.getRequest().setAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE, "/get" + currentContext.getRequest().getRequestURI());
		log.info("New route: {}", currentContext.getRequest().getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE));
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext currentContext = RequestContext.getCurrentContext();
		log.info("Current HTTP method is[{}]: {}", HttpMethod.GET.name(),currentContext.getRequest().getMethod());
		return currentContext.getRequest().getMethod().startsWith(HttpMethod.GET.name());
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
