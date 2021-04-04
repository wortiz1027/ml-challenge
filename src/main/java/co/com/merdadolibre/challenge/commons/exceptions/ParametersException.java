package co.com.merdadolibre.challenge.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParametersException extends RuntimeException {

    public ParametersException() {
        super();
    }

    public ParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParametersException(String message) {
        super(message);
    }

    public ParametersException(Throwable cause) {
        super(cause);
    }

}
