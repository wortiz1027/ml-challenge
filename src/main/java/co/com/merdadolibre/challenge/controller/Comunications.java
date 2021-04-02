package co.com.merdadolibre.challenge.controller;

import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.domain.Satellites;
import co.com.merdadolibre.challenge.domain.services.Request;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.exceptions.DistanceValueException;
import co.com.merdadolibre.challenge.exceptions.MessageNullException;
import co.com.merdadolibre.challenge.exceptions.ServerException;

import co.com.merdadolibre.challenge.exceptions.SateliteNameEmptyOrNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1")
public class Comunications {

    private static final Logger LOG = LoggerFactory.getLogger(Comunications.class);

    @PostMapping("/topsecret")
    public ResponseEntity<Response> decode(@RequestBody(required = true) @Valid Request data) throws DistanceValueException {
        validation(data);

        try {
            Response response = new Response();

            Position position = null;
            position.setX((float) -100.0);
            position.setY((float) 75.5);

            response.setPosition(position);
            response.setMessage("este es un mensaje secreto");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ServerException();
        }
    }

    private void validation(Request data) {
        data.getSatellites().forEach(row -> {
            if (row.getDistance() == 0) throw new DistanceValueException("The ship distance can be cero (0)!");
            if (row.getName().isBlank()) throw new SateliteNameEmptyOrNullException("The satelite name can not be null or empty");
            if (row.getMessage() == null) throw new MessageNullException("The message can not be null");
        });
    }

}
