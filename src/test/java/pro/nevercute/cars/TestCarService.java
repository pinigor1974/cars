package pro.nevercute.cars;

import org.mapstruct.MapperConfig;
import pro.nevercute.cars.adapter.mapping.mapstruct.MapstructConfig;
import pro.nevercute.cars.adapter.mapping.mapstruct.mapper.CarMapper;
import pro.nevercute.cars.application.service.CarRepositoryService;
import pro.nevercute.cars.domain.model.CommandTypeEnum;
import pro.nevercute.cars.adapter.out.http.provider.RostelecomProvider;
import pro.nevercute.cars.application.service.SendService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(
        classes = {CarsApplication.class}
)
@ContextConfiguration(
        initializers = {ContextInitializer.class}
)
public class TestCarService {
    @Autowired
    private CarRepositoryService carRepositoryService;
    @Autowired
    private SendService sendByService;
    @Autowired
    private RostelecomProvider rostelekomProvider;

    @Test
    public void testSendCarCode() {
        // given
        var linkoln = carRepositoryService.findByModelNameAndNumber("linkoln", "У777УУ777");
        var lada = carRepositoryService.findByModelNameAndNumber("lada", "Е001КХ77");

        // when
        var linkolnEnginOffResponse = sendByService.send(CommandTypeEnum.ENGINE_OFF, linkoln);
        var ladaEnginOffResponse = sendByService.send(CommandTypeEnum.ENGINE_OFF, lada);

        // then
        assertEquals(HttpStatus.FORBIDDEN, linkolnEnginOffResponse.httpStatus());
        assertEquals(HttpStatus.BAD_REQUEST, ladaEnginOffResponse.httpStatus());
        Assertions.assertThrows(
                NumberFormatException.class,
                () -> sendByService.send(CommandTypeEnum.ENGINE_ON, lada),
                "NumberFormatException error was expected");
    }

}
