package co.com.merdadolibre.challenge.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SateliteNameEmptyOrNullException extends RuntimeException {

    public SateliteNameEmptyOrNullException() {
        super();
    }

    public SateliteNameEmptyOrNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public SateliteNameEmptyOrNullException(String message) {
        super(message);
    }

    public SateliteNameEmptyOrNullException(Throwable cause) {
        super(cause);
    }

}
