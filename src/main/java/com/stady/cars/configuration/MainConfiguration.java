package com.stady.cars.configuration;

import com.stady.cars.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "app")
@EnableConfigurationProperties({CarApplicationConfiguration.class})

public class MainConfiguration {
    @Bean
    public SendCodeSmsService sendCodeSMSService(){
        return new SendCodeSmsService();
    }
    @Bean
    public SendCodeHttpService sendCodeHttpService(@Autowired RostelecomProvider rostelecomProvider, @Autowired CarApplicationConfiguration carApplicationConfiguration){
        return new SendCodeHttpService(rostelecomProvider, carApplicationConfiguration);
    }

    @Bean
    public SendErrorService sendErrorService(){
        return new SendErrorService();
    }

    @Bean
    public SendByService sendByService(@Autowired  List<SendCodeService> sendCodeServiceList,
                                       @Autowired SendErrorService sendErrorService){
        return new SendByService(sendCodeServiceList, sendErrorService);
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public RostelecomProvider rostelecomProvider(@Autowired  RestTemplate restTemplate) {
        return new RostelecomProvider(restTemplate);
    }
}
