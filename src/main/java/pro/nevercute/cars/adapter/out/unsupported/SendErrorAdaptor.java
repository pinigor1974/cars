package pro.nevercute.cars.adapter.out.unsupported;

import org.springframework.http.HttpStatus;
import pro.nevercute.cars.application.port.out.SendCodePort;
import pro.nevercute.cars.domain.model.CommandTypeEnum;
import pro.nevercute.cars.domain.model.SendType;
import pro.nevercute.cars.domain.port.SendCodeRequest;
import pro.nevercute.cars.domain.port.SendCodeResponse;

public class SendErrorAdaptor implements SendCodePort {

    @Override
    public SendCodeResponse send(SendCodeRequest request) {
        return new SendCodeResponse(HttpStatus.BAD_REQUEST, "Неизвестная ошибка при вызове");
    }

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.ERROR;
    }
}
