package com.softhaxi.marves.core.service.employee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.softhaxi.marves.core.util.MarvesHRUtil;

import static java.util.Map.entry;

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
public class EmployeeVitaeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeVitaeService.class);

    @Autowired
    private MarvesHRUtil marvesHRUtil;

    private List<?> needUpdateUrlKey = List.of("thumbnail", "identity_file");

    private Map<Object, Object> profileMapping = Map.ofEntries(
        entry("FOTO_THUMB", "thumbnail"),
        entry("NAMA_ONLY", "name"),
        entry("NAMA", "full_name"),
        entry("NIP", "employeeNumber"),
        entry("TEMPATLAHIR", "birthPlace"),
        entry("TANGGALLAHIR", "birthDate"),
        entry("JENISKELAMIN", "gender"),
        entry("PANGKATGOLRUANG", "orgLevel"),
        entry("TMT_PNS", "pnsDate"),
        entry("TMT_CPNS", "cpnsDate"),
        entry("JABATAN_AKTIF", "position"),
        entry("AGAMA", "religion"),
        entry("KETPERKAWINAN", "marital"),
        entry("ALAMAT", "street"),
        entry("KELURAHAN", "state"),
        entry("KECAMATAN", "state2"),
        entry("NAMA_KOTAKAB", "city"),
        entry("NAMA_PROVINSI", "province"),
        entry("NOHP", "phoneNumber"),
        entry("EMAIL", "email"),
        entry("KTP", "identityNumber"),
        entry("BPJS", "bpjsNumber"),
        entry("NPWP", "taxNumber"),
        entry("TASPEN", "taspenNumber"),
        entry("KARSU", "karsuNumber"),
        entry("KARPEG", "karpegNumber"),
        entry("B_TINGGI", "height"),
        entry("B_BERAT", "weight"),
        entry("RAMBUT", "hair"),
        entry("KULIT", "skin"),
        entry("MUKA", "face"),
        entry("B_CIRI", "bodyInfo"),
        entry("B_CACAT", "disability"),
        entry("HOBI", "hobby"),
        entry("BANK", "bankName"),
        entry("BANK_REK", "accountNumber"),
        entry("BANK_NAMA", "accountName")
    );

    private Map<Object, Object> educationMapping = Map.ofEntries(
        entry("STRATA", "level"),
        entry("SEKOLAH", "institution"),
        entry("JURUSAN", "major"),
        entry("LULUS", "year"),
        entry("LOKASI", "location"),
        entry("KEPALA", "headmaster")
    );

    private Map<Object, Object> courseMapping = Map.ofEntries(
        entry("KURSUS", "name"),
        entry("TGL_1", "startDate"),
        entry("TGL_2", "endDate"),
        entry("LULUS", "year"),
        entry("LOKASI", "location"),
        entry("KETERANGAN", "remark")
    );

    private Map<Object, Object> trainingMapping = Map.ofEntries(
        entry("DIKLAT", "name"),
        entry("TGLDARI", "startDate"),
        entry("TGLSAMPAI", "endDate"),
        entry("LULUSTAHUN", "year"),
        entry("LOKASI", "location"),
        entry("KETERANGAN", "remark")
    );

    private Map<Object, Object> rankMapping = Map.ofEntries(
        entry("JENIS", "type"),
        entry("PANGKAT", "name"),
        entry("GOLONGAN", "eselon"),
        entry("TMT", "date"),
        entry("GAJI", "salary"),
        entry("SK_NOMOR", "skNumber"),
        entry("SK_PEJABAT", "skOfficer"),
        entry("TANGGAL", "skDate"),
        entry("DASAR", "title")
    );

    private Map<Object, Object> positionMapping = Map.ofEntries(
        entry("JABATAN", "name"),
        entry("ESELON", "eselon"),
        entry("DARI", "startDate"),
        entry("HINGGA", "endDate"),
        entry("NOMOR_SK", "skNumber"),
        entry("SK_PEJABAT", "skOfficer"),
        entry("TANGGAL", "skDate")
    );

    private Map<Object, Object> functionalMapping = Map.ofEntries(
        entry("FUNGSIONAL", "name"),
        entry("DARI", "startDate"),
        entry("ANGKA_KREDIT", "credit"),
        entry("TUNJANGAN", "allowance"),
        entry("ESELON", "eselon"),
        entry("NOMOR_SK", "skNumber"),
        entry("SK_PEJABAT", "skOfficer"),
        entry("TANGGAL", "skDate")
    );

    private Map<Object, Object> rewardMapping = Map.ofEntries(
        entry("NAMA", "name"),
        entry("TAHUN", "year"),
        entry("PEMBERI", "institutionName")
    );

    private Map<Object, Object> performanceMapping = Map.ofEntries(
        entry("TAHUN", "year"),
        entry("NILAI", "value"),
        entry("KETERANGAN", "remark")
    );

    private Map<Object, Object> overseaMapping = Map.ofEntries(
        entry("NEGARA", "country"),
        entry("LOKASI", "location"),
        entry("TUJUAN", "purpose"),
        entry("TAHUN", "year"),
        entry("HARI", "duration")
    );

    private Map<Object, Object> familyMapping = Map.ofEntries(
        entry("F_HUBUNGAN", "relation"),
        entry("NAMAKELUARGA", "name"),
        entry("TEMPAT_LAHIR", "birthPlace"),
        entry("TANGGAL_LAHIR", "birthDate"),
        entry("TANGGAL_NIKAH", "marriedDate"),
        entry("JENISKELAMIN", "gender"),
        entry("PEKERJAAN", "occupation"),
        entry("NAMAPENDIDIKAN", "education"),
        entry("KETERANGAN", "remark")
    );

    private Map<Object, Object> orgMapping = Map.ofEntries(
        entry("KATEGORI", "category"),
        entry("NAMA_ORGANISASI", "name"),
        entry("KEDUDUKAN", "role"),
        entry("DARI", "startYear"),
        entry("HINGGA", "endYear"),
        entry("TEMPAT", "location"),
        entry("PIMPINAN", "leader")
    );

    private Map<Object, Object> otherInfoMapping = Map.ofEntries(
        entry("KATEGORI", "name"),
        entry("PEJABAT", "skOfficer"),
        entry("NOMOR", "skNumber"),
        entry("TANGGAL", "skDate")
    );

    public Map<?, ?> getPersonalInfo(String userId) {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }
        // logger.info("[getPersonalInfo] Token..." + token);
        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_keterangan_perorangan");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        try {
            Map<?, ?> result = (Map<?, ?>) ((List<?>) body.get("result")).get(0);
            Map<Object, Object> data = new HashMap<>();
            profileMapping.entrySet().forEach(entry -> data.put(entry.getValue(), result.get(entry.getKey())));
            needUpdateUrlKey.forEach(item -> data.put(item, String.format("%s%s", marvesHRUtil.getBaseUrl(), data.get(item))));
            // logger.info("[getPersonalInfo] Data..." + data.toString());
            return data;
        } catch(IndexOutOfBoundsException ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    public Map<?, ?> getEducations(String userId) {
        Map<String, Object> data = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_riwayat_pendidikan_by_userid");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> educations = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            educationMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            educations.add(temp);
        });
        data.put("formal", educations);

        form = new LinkedMultiValueMap<>();
        form.add("api", "get_kursus_by_userid");
        form.add("USER_ID", userId.toLowerCase().trim());
        entity = new HttpEntity<>(form, headers);
        response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        body = (Map<?, ?>) response.getBody();
        result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> courses = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            courseMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            courses.add(temp);
        });
        data.put("course", courses);

        form = new LinkedMultiValueMap<>();
        form.add("api", "get_diklat_by_userid");
        form.add("USER_ID", userId.toLowerCase().trim());
        entity = new HttpEntity<>(form, headers);
        response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        body = (Map<?, ?>) response.getBody();
        result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> trainings = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            trainingMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            trainings.add(temp);
        });
        data.put("training", trainings);

        return data;
    }

    public Map<?, ?> getJobs(String userId) {
        Map<String, Object> data = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_riwayat_kepangkatan");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> ranks = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            rankMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            ranks.add(temp);
        });
        data.put("rank", ranks);

        form = new LinkedMultiValueMap<>();
        form.add("api", "get_riwayat_jabatan_by_userid");
        form.add("USER_ID", userId.toLowerCase().trim());
        entity = new HttpEntity<>(form, headers);
        response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        body = (Map<?, ?>) response.getBody();
        result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> positions = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            positionMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            positions.add(temp);
        });
        data.put("position", positions);

        form = new LinkedMultiValueMap<>();
        form.add("api", "get_riwayat_fungsional");
        form.add("USER_ID", userId.toLowerCase().trim());
        entity = new HttpEntity<>(form, headers);
        response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        body = (Map<?, ?>) response.getBody();
        result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> functional = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            functionalMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            functional.add(temp);
        });
        data.put("functional", functional);

        return data;
    }

    public Map<?, ?> getRewards(String userId) {
        Map<String, Object> data = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_tandajasa_by_userid");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> rewards = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            rewardMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            rewards.add(temp);
        });
        data.put("reward", rewards);

        return data;
    }

    public Map<?, ?> getPerformances(String userId) {
        Map<String, Object> data = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_penilaian_prestasi");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> performances = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            performanceMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            performances.add(temp);
        });
        data.put("performance", performances);

        return data;
    }

    public Map<?, ?> getOverseas(String userId) {
        Map<String, Object> data = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_kunjungan_ln");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> overseas = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            overseaMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            overseas.add(temp);
        });
        data.put("oversea", overseas);

        return data;
    }

    public Map<?, ?> getFamilies(String userId) {
        Map<String, Object> data = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_keluarga");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        // logger.info("[getFamily]Result..." + result.toString());
        if(result != null && !result.isEmpty()) {
            List<Map<?, ?>> temp = result.stream()
                .filter(item -> item.get("F_HUBUNGAN") != null
                    && (item.get("F_HUBUNGAN").toString().equalsIgnoreCase("SUAMI") || item.get("F_HUBUNGAN").toString().equalsIgnoreCase("ISTRI")))
                .collect(Collectors.toList());
            Map<Object, Object> spouse = new HashMap<>();
            if(temp != null && !temp.isEmpty()) {
                Map<?, ?> item = temp.get(0);
                familyMapping.entrySet().forEach(entry -> spouse.put(entry.getValue().toString(), item.get(entry.getKey())));
            }
            data.put("spouse", spouse);

            temp = result.stream()
                .filter(item -> item.get("F_HUBUNGAN") != null
                    && item.get("F_HUBUNGAN").toString().equalsIgnoreCase("Anak Kandung"))
                .collect(Collectors.toList());
            List<Map<Object, Object>> children = new LinkedList<>();
            if(temp != null) {
                temp.forEach(item -> {
                    Map<Object, Object> temp1 = new HashMap<>();
                    familyMapping.entrySet().forEach(entry -> temp1.put(entry.getValue().toString(), item.get(entry.getKey())));
                    children.add(temp1);
                });
            }
            data.put("children", children);
            
            temp = result.stream()
                .filter(item -> item.get("F_HUBUNGAN") != null
                && (item.get("F_HUBUNGAN").toString().equalsIgnoreCase("Orang Tua Kandung")
                || item.get("F_HUBUNGAN").toString().equalsIgnoreCase("Ayah Kandung") 
                || item.get("F_HUBUNGAN").toString().equalsIgnoreCase("Ibu Kandung")))
                .collect(Collectors.toList());
            List<Map<Object, Object>> parents = new LinkedList<>();
            if(temp != null) {
                temp.forEach(item -> {
                    Map<Object, Object> temp1 = new HashMap<>();
                    familyMapping.entrySet().forEach(entry -> temp1.put(entry.getValue().toString(), item.get(entry.getKey())));
                    parents.add(temp1);
                });
            }
            data.put("parent", parents);

            temp = result.stream()
                .filter(item -> item.get("F_HUBUNGAN") != null
                && (item.get("F_HUBUNGAN").toString().equalsIgnoreCase("Saudari Kandung")
                || item.get("F_HUBUNGAN").toString().equalsIgnoreCase("Saudara Kandung")))
                .collect(Collectors.toList());
            List<Map<Object, Object>> siblings = new LinkedList<>();
            if(temp != null) {
                temp.forEach(item -> {
                    Map<Object, Object> temp1 = new HashMap<>();
                    familyMapping.entrySet().forEach(entry -> temp1.put(entry.getValue().toString(), item.get(entry.getKey())));
                    siblings.add(temp1);
                });
            }
            data.put("sibling", siblings);

            temp = result.stream()
                .filter(item -> item.get("F_HUBUNGAN") != null
                && item.get("F_HUBUNGAN").toString().equalsIgnoreCase("Mertua"))
                .collect(Collectors.toList());
            List<Map<Object, Object>> parentInLaws = new LinkedList<>();
            if(temp != null) {
                temp.forEach(item -> {
                    Map<Object, Object> temp1 = new HashMap<>();
                    familyMapping.entrySet().forEach(entry -> temp1.put(entry.getValue().toString(), item.get(entry.getKey())));
                    parentInLaws.add(temp1);
                });
            }
            data.put("parentInLaw", parentInLaws);
        }

        return data;
    }

    public Map<?, ?> getOrganizations(String userId) {
        Map<String, Object> data = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_organisasi");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        // logger.info("[getFamily]Result..." + result.toString());
        List<Map<?, ?>> temp = result.stream()
            .filter(item -> ((String)item.get("KATEGORI")).equalsIgnoreCase("Semasa mengikuti pendidikan pada SMA kebawah"))
            .collect(Collectors.toList());
        List<Map<Object, Object>> highSchools = new LinkedList<>();
        if(temp != null) {
            temp.forEach(item -> {
                Map<Object, Object> temp1 = new HashMap<>();
                orgMapping.entrySet().forEach(entry -> {
                    if(entry.getKey().toString().equals("HINGGA")) {
                        if(item.get(entry.getKey()).toString().equals("0")) {
                            temp1.put(entry.getValue().toString(), null);
                        }
                    } else {
                        temp1.put(entry.getValue().toString(), item.get(entry.getKey()));
                    }
                });
                highSchools.add(temp1);
            });
        }
        data.put("highSchool", highSchools);

        temp = result.stream()
            .filter(item -> ((String)item.get("KATEGORI")).toString().equalsIgnoreCase("Semasa mengikuti pendidikan pada perguruan tinggi"))
            .collect(Collectors.toList());
        List<Map<Object, Object>> colleges = new LinkedList<>();
        if(temp != null) {
            temp.forEach(item -> {
                Map<Object, Object> temp1 = new HashMap<>();
                orgMapping.entrySet().forEach(entry -> {
                    if(entry.getKey().toString().equals("HINGGA")) {
                        if(item.get(entry.getKey()).toString().equals("0")) {
                            temp1.put(entry.getValue().toString(), null);
                        }
                    } else {
                        temp1.put(entry.getValue().toString(), item.get(entry.getKey()));
                    }
                });
                colleges.add(temp1);
            });
        }
        data.put("college", colleges);
        
        temp = result.stream()
            .filter(item -> ((String)item.get("KATEGORI")).toString().equalsIgnoreCase("Sesudah selesai pendidkan dan atau selama menjadi pegawai"))
            .collect(Collectors.toList());
        List<Map<Object, Object>> offices = new LinkedList<>();
        if(temp != null) {
            temp.forEach(item -> {
                Map<Object, Object> temp1 = new HashMap<>();
                orgMapping.entrySet().forEach(entry -> {
                    if(entry.getKey().toString().equals("HINGGA")) {
                        if(item.get(entry.getKey()).toString().equals("0")) {
                            temp1.put(entry.getValue().toString(), null);
                        } else {
                            temp1.put(entry.getValue().toString(), item.get(entry.getKey()));
                        }
                    } else {
                        temp1.put(entry.getValue().toString(), item.get(entry.getKey()));
                    }
                });
                offices.add(temp1);
            });
        }
        data.put("office", offices);

        return data;
    }

    public Map<?, ?> getOtherInfos(String userId) {
        Map<String, Object> data = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String token = marvesHRUtil.getAccessToken();
        if(token == null) {
            return null;
        }

        headers.add("token", token);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("api", "get_keterangan_lain");
        form.add("USER_ID", userId.toLowerCase().trim());
        HttpEntity<Map<?, ?>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(String.format("%s%s", marvesHRUtil.getBaseUrl(), marvesHRUtil.getApiEndpoint()), 
            entity, 
            Map.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        List<Map<?, ?>> result = (List<Map<?, ?>>) body.get("result");
        List<Map<?, ?>> infos = new LinkedList<>();
        result.forEach(item -> {
            Map<Object, Object> temp = new HashMap<>();
            otherInfoMapping.entrySet().forEach(entry -> temp.put(entry.getValue().toString(), item.get(entry.getKey())));
            infos.add(temp);
        });
        data.put("info", infos);

        return data;
    }

}
