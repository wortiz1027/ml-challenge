package co.com.merdadolibre.challenge.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class SatelliteNameAlreadyExistsException extends RuntimeException {

    public SatelliteNameAlreadyExistsException() {
        super();
    }

    public SatelliteNameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SatelliteNameAlreadyExistsException(String message) {
        super(message);
    }

    public SatelliteNameAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}