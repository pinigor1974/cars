package pro.nevercute.cars.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EngineSwitchedOnCarEvent extends CarEvent {

    public EngineSwitchedOnCarEvent(String carNumber) {
        super(carNumber);
    }
}
