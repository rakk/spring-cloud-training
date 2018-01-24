package com.gft.academy.selen.ui.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

public class OAuthFeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthFeignRequestInterceptor.class);

    private final OAuth2ClientContext oauth2ClientContext;

    public OAuthFeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
        this.oauth2ClientContext = oauth2ClientContext;
    }

    @Override
    public void apply(RequestTemplate template) {

        if (template.headers().containsKey(AUTHORIZATION_HEADER)) {
            LOGGER.warn("The Authorization token has been already set");
        } else if (oauth2ClientContext.getAccessTokenRequest().getExistingToken() == null && oauth2ClientContext.getAccessToken() == null) {
            LOGGER.warn("Can not obtain existing token for request, if it is a non secured request, ignore.");
        } else {
            Object token = ObjectUtils.defaultIfNull(oauth2ClientContext.getAccessTokenRequest().getExistingToken(), oauth2ClientContext.getAccessToken());
            LOGGER.debug("Constructing Header {} for Token {}", AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE);
            template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,
                    token.toString()));
        }
    }
}
