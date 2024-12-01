package com.stady.cars.configuration;

import com.stady.cars.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

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
    public SendCodeHttpService sendCodeHttpService(){
        return new SendCodeHttpService();
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
}
