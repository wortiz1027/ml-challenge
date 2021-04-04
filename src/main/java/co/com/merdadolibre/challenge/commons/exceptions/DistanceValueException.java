package co.com.merdadolibre.challenge.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DistanceValueException extends RuntimeException {

    public DistanceValueException() {
        super();
    }

    public DistanceValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistanceValueException(String message) {
        super(message);
    }

    public DistanceValueException(Throwable cause) {
        super(cause);
    }

}