package com.gft.academy.routing.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class PreLogFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PreLogFilter.class);

	@Override
	public Object run() {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		log.info("Request URI: {}", request.getRequestURI());
		log.debug("AuthType: {}, Method: {}, Query: {}", request.getAuthType(), request.getMethod(), request.getQueryString());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}
}
