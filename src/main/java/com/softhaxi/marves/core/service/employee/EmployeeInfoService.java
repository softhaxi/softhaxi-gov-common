package com.softhaxi.marves.core.service.employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

import com.softhaxi.marves.core.util.MarvesHRUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Marves HR service part
 */
@Service
@SuppressWarnings("unchecked")
public class EmployeeInfoService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeInfoService.class);

    @Autowired
    private MarvesHRUtil marvesHRUtil;

    private Map<Object, Object> employeeMapping = Map.ofEntries(
        entry("nama", "name"),
        entry("NIP", "employeeNumber"),
        entry("emailuser", "email")
    );

    private Map<Object, Object> birthdayMapping = Map.ofEntries(
        entry("NAMA_ONLY", "name"),
        entry("LAHIR_TGL", "birthDate"),
        entry("EMAIL", "email"),
        entry("UNITKERJA", "unit")
    );

    public Collection<?> getEmployeeList() {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }
        
        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_list_all_staff");
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> data = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            employeeMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            data.add(temp);
        });
        return data;
    }

    public Collection<?> getTodayBirthdayList() {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }
        
        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_whois_birthday");
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> data = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            birthdayMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            data.add(temp);
        });
        return data;
    }
}
