package com.gft.academy.routing.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

public class VersionRouteFilter extends ZuulFilter {

    private final static String API_VERSION_HEADER_NAME = "API-Version";
    private static Logger log = LoggerFactory.getLogger(VersionRouteFilter.class);

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        String version = currentContext.getRequest().getHeader(API_VERSION_HEADER_NAME);

        log.debug("Old route: {}", currentContext.getRequest().getRequestURI());
        currentContext.getRequest().setAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE, "/" + version + currentContext.getRequest().getRequestURI());
        log.debug("New route: {}", currentContext.getRequest().getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE));

        return null;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        log.info("Detected API Version: {} for URI: {}", currentContext.getRequest().getHeader(API_VERSION_HEADER_NAME), currentContext.getRequest().getRequestURI());
        return currentContext.getRequest().getHeader(API_VERSION_HEADER_NAME) != null;
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