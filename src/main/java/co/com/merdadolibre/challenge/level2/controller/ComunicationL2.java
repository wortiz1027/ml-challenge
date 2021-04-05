package co.com.merdadolibre.challenge.level2.controller;

import co.com.merdadolibre.challenge.domain.services.level2.RequestL2;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.commons.exceptions.DistanceValueException;
import co.com.merdadolibre.challenge.commons.exceptions.MessageNullException;
import co.com.merdadolibre.challenge.commons.exceptions.ServerException;

import co.com.merdadolibre.challenge.commons.exceptions.SateliteNameEmptyOrNullException;
import co.com.merdadolibre.challenge.level2.services.IData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(value="Decodificar mensaje interceptado por los satelites de la alianza rebelde")
public class ComunicationL2 {

    private static final Logger LOG = LoggerFactory.getLogger(ComunicationL2.class);

    private final IData idata;

    @ApiOperation(value = "Decodificacion del mensaje interceptado", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Decodificacion exitosa"),
            @ApiResponse(code = 400, message = "Error en la informacion enviada"),
            @ApiResponse(code = 404, message = "Error decodificando el mensaje"),
            @ApiResponse(code = 500, message = "Error interno en el servidor")
    })
    @PostMapping("/topsecret")
    public ResponseEntity<Response> decode(@RequestBody(required = true) RequestL2 data) {
        validation(data);

        try {
            Response response = idata.processSatellitesData(data);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ServerException();
        }
    }

    @ApiOperation(value = "HealthCheck para validar la operatividad del servicio", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "HealthCheck correcto")
    })
    @GetMapping("/")
    public ResponseEntity<String> healthCheck() {
        String message = String.format("Service ml-challenge is up! %s", LocalDateTime.now());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private void validation(RequestL2 data) {
        data.getSatellites().forEach(row -> {
            if (row.getDistance() == 0) throw new DistanceValueException("The ship distance can be cero (0)!");
            if (row.getName().isEmpty()) throw new SateliteNameEmptyOrNullException("The satelite name can not be null or empty");
            if (row.getMessage() == null) throw new MessageNullException("The message can not be null");
        });
    }

}
