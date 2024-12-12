package pro.nevercute.cars.configuration;

import pro.nevercute.cars.adapter.out.http.provider.RostelecomProvider;
import pro.nevercute.cars.application.service.SendService;
import pro.nevercute.cars.adapter.out.http.SendCodeHttpAdapter;
import pro.nevercute.cars.application.port.out.SendCodePort;
import pro.nevercute.cars.adapter.out.sms.SendCodeSmsAdapter;
import pro.nevercute.cars.adapter.out.unsupported.SendErrorAdaptor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MainConfiguration {

    @Bean
    public SendCodeSmsAdapter sendCodeSMSService(){
        return new SendCodeSmsAdapter();
    }
    @Bean
    public SendCodeHttpAdapter sendCodeHttpService(RostelecomProvider rostelecomProvider, CarApplicationProperties carApplicationConfiguration){
        return new SendCodeHttpAdapter(rostelecomProvider, carApplicationConfiguration);
    }

    @Bean
    public SendErrorAdaptor sendErrorService(){
        return new SendErrorAdaptor();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public RostelecomProvider rostelecomProvider(RestTemplate restTemplate) {
        return new RostelecomProvider(restTemplate);
    }
}
