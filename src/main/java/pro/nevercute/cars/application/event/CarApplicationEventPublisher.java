package pro.nevercute.cars.application.event;

import pro.nevercute.cars.domain.event.CarEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarApplicationEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishEvent(CarEvent event) {
        eventPublisher.publishEvent(event);
    }
}
