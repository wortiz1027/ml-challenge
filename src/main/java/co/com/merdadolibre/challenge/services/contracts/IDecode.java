package co.com.merdadolibre.challenge.services.contracts;

import org.springframework.stereotype.Component;

import java.util.List;

public interface IDecode {

    String getMessage(List<String[]> messages);

}
