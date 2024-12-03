package com.stady.cars.service;

import com.stady.cars.domain.model.CommandTypeEnum;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RostelecomProvider {
    private RestTemplate restTemplate;
    public RostelecomProvider(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public CommandTypeEnum send(String uri, CommandTypeEnum code) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var req  =RequestEntity.post(uri).headers(headers).body(code);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        ResponseEntity<CommandTypeEnum> response =  restTemplate.exchange(req, CommandTypeEnum.class);
        return response.getBody();
    }

}
