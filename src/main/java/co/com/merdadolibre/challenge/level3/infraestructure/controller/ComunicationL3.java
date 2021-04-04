package co.com.merdadolibre.challenge.level3.infraestructure.controller;

import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.domain.services.level3.Request;
import co.com.merdadolibre.challenge.commons.exceptions.DistanceValueException;
import co.com.merdadolibre.challenge.commons.exceptions.MessageNullException;
import co.com.merdadolibre.challenge.commons.exceptions.ServerException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ComunicationL3 {

    private static final Logger LOG = LoggerFactory.getLogger(ComunicationL3.class);

    @PostMapping("/topsecret_split/{name}")
    public ResponseEntity<Response> decodePost(@PathVariable("name") String name) {
        //validation(data);

        try {
            Response response = new Response();

            Position position = new Position();
            position.setX((float) -100.0);
            position.setY((float) 75.5);

            response.setPosition(position);
            response.setMessage("este es un mensaje secreto");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ServerException();
        }
    }

    @GetMapping("/topsecret_split/{id}")
    public ResponseEntity<Response> decodeGet(@RequestParam String name, @RequestParam float distance, @RequestParam String[] message) {
        //validation(data);

        try {

            for (String s : message) LOG.debug("---> {}", s);

            Response response = new Response();

            Position position = new Position();
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
        if (data.getDistance() == 0) throw new DistanceValueException("The ship distance can be cero (0)!");
        if (data.getMessage() == null) throw new MessageNullException("The message can not be null");
    }

}
