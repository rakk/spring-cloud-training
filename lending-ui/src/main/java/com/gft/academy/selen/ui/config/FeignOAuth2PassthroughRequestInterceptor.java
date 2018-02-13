package com.gft.academy.selen.ui.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignOAuth2PassthroughRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignOAuth2PassthroughRequestInterceptor.class);

    public FeignOAuth2PassthroughRequestInterceptor() {
    }

    @Override
    public void apply(RequestTemplate template) {
//        if (template.headers().containsKey(AUTHORIZATION_HEADER)) {
//            LOGGER.warn("The Authorization token has been already set");
//        } else {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (authentication instanceof OAuth2Authentication) {
//                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
//                LOGGER.debug("Constructing Header {} for Token {}", AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE);
//                template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,
//                        details.getTokenValue()));
//            } else {
//                LOGGER.warn("Missing OAuth2 Access token");
//            }
//        }
    }
}
