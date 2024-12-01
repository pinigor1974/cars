package com.stady.cars;

import com.stady.cars.configuration.CarConfiguration;
import com.stady.cars.configuration.MainConfiguration;
import com.stady.cars.data.Cars;
import com.stady.cars.data.Codes;
import com.stady.cars.service.SendByService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;



@SpringBootTest
public class TestCarService {
    @Autowired
    private CarConfiguration carConfiguration;
    @Autowired
    private SendByService sendByService;

    @Test
    public void testSendCarCode() {
       Assertions.assertEquals(sendByService.send(Codes.engineOff, carConfiguration.getCars().get(Cars.linkoln.name())), Codes.engineOff);
       Assertions.assertEquals(sendByService.send(Codes.engineOff, carConfiguration.getCars().get(Cars.lada.name())), Codes.error);

        Assertions.assertThrows(NumberFormatException.class, () -> {
            sendByService.send(Codes.engineOn, carConfiguration.getCars().get(Cars.lada.name()));
        }, "NumberFormatException error was expected");
    }
}
