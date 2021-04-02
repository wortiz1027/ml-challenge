package co.com.merdadolibre.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PositionNotFoundException extends RuntimeException {

    public PositionNotFoundException() {
        super();
    }

    public PositionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PositionNotFoundException(String message) {
        super(message);
    }

    public PositionNotFoundException(Throwable cause) {
        super(cause);
    }

}
