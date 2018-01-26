package com.gft.academy.selen.oauth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

public class FacebookRemoteTokenInfoTokenServices implements ResourceServerTokenServices {

    private static final String INPUT_TOKEN_REQUEST_PARAM = "input_token";
    private static final String ACCESS_TOKEN_REQUEST_PARAM = "access_token";
    private final Log logger = LogFactory.getLog(this.getClass());

    private String checkTokenEndpointUrl;
    private String clientId;
    private String clientSecret;

    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
    private RestOperations restTemplate = new RestTemplate();

    public FacebookRemoteTokenInfoTokenServices(String checkTokenEndpointUrl, String clientId, String clientSecret) {
        this.checkTokenEndpointUrl = checkTokenEndpointUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        URI requestUri = UriComponentsBuilder.fromUriString(checkTokenEndpointUrl)
                .queryParam(INPUT_TOKEN_REQUEST_PARAM, accessToken)
                .queryParam(ACCESS_TOKEN_REQUEST_PARAM, clientId+"|"+clientSecret)
                .build().encode().toUri();

        Map map = this.restTemplate.exchange(requestUri, HttpMethod.GET, new HttpEntity(new HttpHeaders()), Map.class).getBody();
        if (map.containsKey("error")) {
            logger.debug("check_token returned error: " + map.get("error"));
            throw new InvalidTokenException(accessToken);
        } else {
            return tokenConverter.extractAuthentication(map);
        }
    }

    @Override
    public OAuth2AccessToken readAccessToken(String s) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

}
