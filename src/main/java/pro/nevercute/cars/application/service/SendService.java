package pro.nevercute.cars.application.service;

import pro.nevercute.cars.adapter.out.unsupported.SendErrorAdaptor;
import pro.nevercute.cars.application.port.out.SendCodePort;
import pro.nevercute.cars.configuration.ApplicationService;
import pro.nevercute.cars.domain.model.Car;
import pro.nevercute.cars.domain.model.CommandTypeEnum;
import pro.nevercute.cars.domain.model.SendType;
import pro.nevercute.cars.domain.port.SendCodeRequest;
import pro.nevercute.cars.domain.port.SendCodeResponse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@ApplicationService
public class SendService {
    private List<SendCodePort> sendCodePortList; // Fixed ToDo отсортировать по приоритету либо отсортировать ниже по типу
    private SendErrorAdaptor sendErrorService;

    public SendService(List<SendCodePort> sendCodePortList,
                       SendErrorAdaptor sendErrorService) {
        this.sendCodePortList = sendCodePortList;
        // не понимаю, почему error отдельно? это такой же порт для отправки, но для случая, когда не нашли поддерживаемый тип отправки
        // вообще видится sortedHashMap, где мы можем сортировать по параметру типа SendType (priority)
        Comparator<SendCodePort> comp = (s1, s2) -> {
            Optional<SendType> sendType1 = Arrays.stream(SendType.values()).filter(sendType -> s1.supports(sendType)).findAny();
            Optional<SendType> sendType2 = Arrays.stream(SendType.values()).filter(sendType -> s2.supports(sendType)).findAny();
            return sendType1.flatMap(st1 -> sendType2.map(st2 -> st1.getValue().compareTo(st2.getValue()))).get();
        };
        this.sendErrorService = sendErrorService;
    }

    public SendCodeResponse send(CommandTypeEnum code, Car car) {
        if (!car.codes().contains(code)) {
            throw new NumberFormatException("error codes");
        }
        var request = new SendCodeRequest(code);
        var service = getPriorityService(car);
        try {
            return service.send(request);
        } catch (Exception e) {
            var services = sendCodePortList.stream().filter(sendService ->
                    car.sendTypes().stream().anyMatch(sendService::supports)
            ).toList();
            for (SendCodePort srv : services) {
                try {
                    return srv.send(request);
                } catch (Exception ignored) {
                }
            }
        }
        throw new RuntimeException();
    }

    private SendCodePort getPriorityService(Car car) {
        return sendCodePortList.stream()
                .filter(service -> service.supports(car.prioritizedSendType()))
                .findFirst()
                .orElse(sendErrorService);

    }


}
