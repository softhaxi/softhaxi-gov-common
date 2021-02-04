package com.softhaxi.marves.core.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OneSignalConfiguration {

    @Value("${onesignal.secret.key}")
    private String secretKey;

    @Bean("oneSignalRestTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.additionalInterceptors(new ClientHttpRequestInterceptor(){

			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
                request.getHeaders().set("Authorization", String.format("Basic %s", secretKey));
                return execution.execute(request, body);
			}
            
        }).build();
    }
}
