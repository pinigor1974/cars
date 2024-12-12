package pro.nevercute.cars.application.port.out;

import pro.nevercute.cars.domain.model.SendType;
import pro.nevercute.cars.domain.port.SendCodeRequest;
import pro.nevercute.cars.domain.port.SendCodeResponse;

public interface SendCodePort {
    SendCodeResponse send(SendCodeRequest request);
    Boolean supports(SendType sendType);

}
