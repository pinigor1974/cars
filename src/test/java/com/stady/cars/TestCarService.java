package com.stady.cars;

import com.stady.cars.configuration.CarApplicationConfiguration;
import com.stady.cars.domain.model.Cars;
import com.stady.cars.domain.model.Codes;
import com.stady.cars.service.SendByService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestCarService {
    @Autowired
    private CarApplicationConfiguration carApplicationConfiguration;
    @Autowired
    private SendByService sendByService;

    @Test
    public void testSendCarCode() {
       Assertions.assertEquals(sendByService.send(Codes.engineOff, carApplicationConfiguration.getCars().get(Cars.LINKOLN.name())), Codes.engineOff);
       Assertions.assertEquals(sendByService.send(Codes.engineOff, carApplicationConfiguration.getCars().get(Cars.LADA.name())), Codes.error);

        Assertions.assertThrows(NumberFormatException.class, () -> {
            sendByService.send(Codes.engineOn, carApplicationConfiguration.getCars().get(Cars.LADA.name()));
        }, "NumberFormatException error was expected");
    }
}
