package pro.nevercute.cars.application.port.in.use_case;

import pro.nevercute.cars.domain.command.SwitchEngineOnCommand;
import pro.nevercute.cars.domain.command.result.SwitchEngineOnResult;

public interface SwitchEngineOffUseCase {
    SwitchEngineOnResult putEngineOff(SwitchEngineOnCommand command);
}
