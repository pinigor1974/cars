package pro.nevercute.cars.domain.port;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public record SendCodeResponse(HttpStatus httpStatus, String error) {

    public boolean hasError() {
        return StringUtils.hasText(error);
    }
}
