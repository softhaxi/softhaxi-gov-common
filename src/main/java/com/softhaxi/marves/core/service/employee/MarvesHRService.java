package com.softhaxi.marves.core.service.employee;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class MarvesHRService {
    private static final Logger logger = LoggerFactory.getLogger(MarvesHRService.class);

    @Value("${marves.hr.url}")
    private String marvesHrUrl;

    @Value("${marves.hr.username}")
    private String marvesHrUsername;

    @Value("${marves.hr.secret}")
    private String marvesHrSecret;

    @Value("${marves.hr.token.endpoint}")
    private String marvesHrTokenEndpoint;

    @Value("${marves.hr.api.endpoint}")
    private String marvesHrApiEndpoint;

    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("username", marvesHrUsername);
        form.add("secret", marvesHrSecret);
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHrUrl, marvesHrTokenEndpoint), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> data = (Map<?, ?>) response.getBody();
        return data.get("token").toString().trim();
    }

    public Map<?, ?> getPersonalInfo(String userId) {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = getAccessToken();
        if(token == null) {
            return null;
        }
        // logger.info("[getPersonalInfo] Token..." + token);
        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_drh_by_userid");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHrUrl, marvesHrApiEndpoint), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> data = (Map<?, ?>) response.getBody();
        // logger.info("[getPersonalInfo] Data..." + data.toString());
        return data;
    }
}
