package co.com.merdadolibre.challenge.level2.controller;

import co.com.merdadolibre.challenge.domain.services.level2.Request;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.commons.exceptions.DistanceValueException;
import co.com.merdadolibre.challenge.commons.exceptions.MessageNullException;
import co.com.merdadolibre.challenge.commons.exceptions.ServerException;

import co.com.merdadolibre.challenge.commons.exceptions.SateliteNameEmptyOrNullException;
import co.com.merdadolibre.challenge.level2.services.IData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ComunicationL2 {

    private static final Logger LOG = LoggerFactory.getLogger(ComunicationL2.class);

    private final IData idata;

    @PostMapping("/topsecret")
    public ResponseEntity<Response> decode(@RequestBody(required = true) Request data) {
        validation(data);

        try {
            Response response = idata.processSatellitesData(data);

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
