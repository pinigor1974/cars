package pro.nevercute.cars.adapter.out.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import pro.nevercute.cars.application.port.out.SendCodePort;
import pro.nevercute.cars.adapter.out.http.provider.RostelecomProvider;
import pro.nevercute.cars.configuration.CarApplicationProperties;
import pro.nevercute.cars.domain.model.CommandTypeEnum;
import pro.nevercute.cars.domain.model.SendType;
import pro.nevercute.cars.domain.model.Services;
import pro.nevercute.cars.domain.port.SendCodeRequest;
import pro.nevercute.cars.domain.port.SendCodeResponse;

@Slf4j
public class SendCodeHttpAdapter implements SendCodePort {

    private RostelecomProvider rostelekomProvider; // Я бы придумал какой-то resolver провайдера
    private CarApplicationProperties carApplicationConfiguration;
    public SendCodeHttpAdapter(RostelecomProvider rostelecomProvider, CarApplicationProperties carApplicationConfiguration){
        this.rostelekomProvider = rostelecomProvider;
        this.carApplicationConfiguration = carApplicationConfiguration;
    }
// Fixed ToDO в реализации какой-то restTemplate клиент


    @Override
    public SendCodeResponse send(SendCodeRequest request) {
        try {
            // ToDo здесь надо бы научиться разделять параметры приложения от параметров адаптера
            rostelekomProvider.send(carApplicationConfiguration.getServices().get(Services.ROSTELECOM), request.code()); //Done ToDo представим, что здесь мы хотим реально сходить во внешний сервис
            return new SendCodeResponse(HttpStatus.OK, null);
        }catch (Exception e){
            log.error("Error send {}", request.code().name());
            return new SendCodeResponse(HttpStatus.FORBIDDEN, "Ошибка доступа к API");
        }
    }

    //Fixed ToDo реализовать какой-то стаб сервис, который реально должен вызываться (в тестах, в реализации пусть остается
    //  какой-то интерфейс, который и вызывается. В тестах подключить wiremock и определить request|response и протестировать интеграцию

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.HTTP;
    }



}
