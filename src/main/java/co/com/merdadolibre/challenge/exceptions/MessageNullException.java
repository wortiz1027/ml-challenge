package co.com.merdadolibre.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MessageNullException extends RuntimeException {

    public MessageNullException() {
        super();
    }

    public MessageNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageNullException(String message) {
        super(message);
    }

    public MessageNullException(Throwable cause) {
        super(cause);
    }

}