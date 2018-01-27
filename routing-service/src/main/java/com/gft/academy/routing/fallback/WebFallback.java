package com.gft.academy.routing.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WebFallback implements FallbackProvider{

	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("Custom web fallback".getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.INTERNAL_SERVER_ERROR.value();
			}
			
			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public String getRoute() {
		return "web";
	}

	@Override
	public ClientHttpResponse fallbackResponse(Throwable cause) {
		return fallbackResponse();
	}

}
