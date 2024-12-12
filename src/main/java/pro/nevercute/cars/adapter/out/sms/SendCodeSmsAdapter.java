package pro.nevercute.cars.adapter.out.sms;

import pro.nevercute.cars.application.port.out.SendCodePort;
import pro.nevercute.cars.domain.model.SendType;
import pro.nevercute.cars.domain.port.SendCodeRequest;
import pro.nevercute.cars.domain.port.SendCodeResponse;
import org.springframework.http.HttpStatus;

public class SendCodeSmsAdapter implements SendCodePort {

    @Override
    public SendCodeResponse send(SendCodeRequest request) {
        return new SendCodeResponse(HttpStatus.OK, null);
    }

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.SMS;
    }
}
