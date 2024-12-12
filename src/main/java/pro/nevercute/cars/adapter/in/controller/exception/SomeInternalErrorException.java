package pro.nevercute.cars.adapter.in.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SomeInternalErrorException extends RuntimeException{

    public SomeInternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
