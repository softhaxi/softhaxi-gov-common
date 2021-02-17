package com.softhaxi.marves.core.service.employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

import com.softhaxi.marves.core.util.MarvesHRUtil;

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

@Service
public class EmployeeDivisionService {
    @Autowired
    private MarvesHRUtil marvesHRUtil;

    @Autowired
    private RestTemplate restTemplate;

    private Map<Object, Object> divisionMapping = Map.ofEntries(
        entry("id", "id"),
        entry("SINGKATAN", "code"),
        entry("NAMAPENDEK", "name"),
        entry("NAMA", "description"),
        entry("JENIS_ES1", "type")
    );

    private Map<Object, Object> employeeMapping = Map.ofEntries(
        entry("EMAIL", "email"),
        entry("NAMAPENDEK", "name"),
        entry("UNITKERJA", "division")
    );

    public Collection<Map<?, ?>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_group");
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
            divisionMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            data.add(temp);
        });
        return data;
    }

    public Collection<Map<?, ?>> findEmployeeByDivision(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_user_by_group");
        form.add("ESELON1_ID", id);
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
}
