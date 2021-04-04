package co.com.merdadolibre.challenge.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MessageNotDecodeException extends RuntimeException {

    public MessageNotDecodeException() {
        super();
    }

    public MessageNotDecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageNotDecodeException(String message) {
        super(message);
    }

    public MessageNotDecodeException(Throwable cause) {
        super(cause);
    }
}
