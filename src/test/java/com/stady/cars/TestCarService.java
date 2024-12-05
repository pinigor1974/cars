package com.stady.cars;

import com.stady.cars.configuration.CarApplicationProperties;
import com.stady.cars.domain.model.Cars;
import com.stady.cars.domain.model.CommandTypeEnum;
import com.stady.cars.adapter.out.http.provider.RostelecomProvider;
import com.stady.cars.application.service.SendService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ContextConfiguration(initializers = {ContextInitializer.class})
public class TestCarService {
    @Autowired
    private CarApplicationProperties carApplicationConfiguration;
    @Autowired
    private SendService sendByService;
    @Autowired
    private RostelecomProvider rostelekomProvider;

    @Test
    public void testSendCarCode() {
       assertEquals(sendByService.send(CommandTypeEnum.ENGINE_OFF, carApplicationConfiguration.getCars().get(Cars.LINKOLN)), CommandTypeEnum.ENGINE_OFF);
       assertEquals(sendByService.send(CommandTypeEnum.ENGINE_OFF, carApplicationConfiguration.getCars().get(Cars.LADA)), CommandTypeEnum.ERROR);

        Assertions.assertThrows(NumberFormatException.class, () -> {
            sendByService.send(CommandTypeEnum.ENGINE_ON, carApplicationConfiguration.getCars().get(Cars.LADA));
        }, "NumberFormatException error was expected");
    }

}
