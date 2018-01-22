package com.gft.academy.routing.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreLogFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PreLogFilter.class);

	@Override
	public Object run() {
		RequestContext currentContext = RequestContext.getCurrentContext();
		log.info("Method: {}",currentContext.getRequest().getMethod());		
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
