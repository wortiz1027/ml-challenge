package co.com.merdadolibre.challenge.domain.services;

import co.com.merdadolibre.challenge.domain.Position;
import lombok.Data;

@Data
public class Response implements java.io.Serializable {

    private Position position;
    private String message;

}
