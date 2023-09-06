package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.domain.CheckFileDto;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Repository
public class DrAppClientImpl implements DrAppClient {

    @Override
    public CheckFileDto getCheckFile(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        HttpEntity<CheckFileDto> entity = new HttpEntity<>(headers);
        HttpEntity<CheckFileDto> result = restTemplate
                .exchange("https://dsdata.drapp.com.ar/apx/checkfile", HttpMethod.GET,
                entity, CheckFileDto.class);
        return result.getBody();
    }

    @Override
    public byte[] getCodeBook(String token) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("multipart/form-data"));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("https://dsdata.drapp.com.ar/apx/lastcsv", HttpMethod.GET,
                entity,  byte[].class).getBody();

    }

}


