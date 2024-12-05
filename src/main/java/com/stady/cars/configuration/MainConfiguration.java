package com.stady.cars.configuration;

import com.stady.cars.adapter.out.http.provider.RostelecomProvider;
import com.stady.cars.application.service.SendService;
import com.stady.cars.adapter.out.http.SendCodeHttpAdapter;
import com.stady.cars.application.port.out.SendCodePort;
import com.stady.cars.adapter.out.sms.SendCodeSmsAdapter;
import com.stady.cars.adapter.out.unsupported.SendErrorAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "app")
@EnableConfigurationProperties({CarApplicationProperties.class})

public class MainConfiguration {
    @Bean
    public SendCodeSmsAdapter sendCodeSMSService(){
        return new SendCodeSmsAdapter();
    }
    @Bean
    public SendCodeHttpAdapter sendCodeHttpService(@Autowired RostelecomProvider rostelecomProvider, @Autowired CarApplicationProperties carApplicationConfiguration){
        return new SendCodeHttpAdapter(rostelecomProvider, carApplicationConfiguration);
    }

    @Bean
    public SendErrorAdaptor sendErrorService(){
        return new SendErrorAdaptor();
    }

    @Bean
    public SendService sendByService(@Autowired  List<SendCodePort> sendCodePortList,
                                     @Autowired SendErrorAdaptor sendErrorService){
        return new SendService(sendCodePortList, sendErrorService);
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
