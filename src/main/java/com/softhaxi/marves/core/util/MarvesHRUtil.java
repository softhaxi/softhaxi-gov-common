package com.softhaxi.marves.core.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class MarvesHRUtil {
    @Value("${marves.hr.url}")
    private String baseUrl;

    @Value("${marves.hr.username}")
    private String username;

    @Value("${marves.hr.secret}")
    private String secret;

    @Value("${marves.hr.token.endpoint}")
    private String tokenEndpoint;

    @Value("${marves.hr.api.endpoint}")
    private String apiEndpoint;

    public String getAccessToken() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            form.add("username", username);
            form.add("secret", secret);
            HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
            ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", baseUrl, tokenEndpoint), 
                entity, 
                Map.class);
            if(response.getStatusCode() != HttpStatus.OK) {
                return null;
            }
            Map<?, ?> data = (Map<?, ?>) response.getBody();
            return data.get("token").toString().trim();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getUsername() {
        return this.username;
    }

    public String getSecret() {
        return this.secret;
    }

    public String getTokenEndpoint() {
        return this.tokenEndpoint;
    }

    public String getApiEndpoint() {
        return this.apiEndpoint;
    }
}
