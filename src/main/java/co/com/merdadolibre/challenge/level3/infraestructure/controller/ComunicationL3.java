package co.com.merdadolibre.challenge.level3.infraestructure.controller;

import co.com.merdadolibre.challenge.commons.exceptions.ParametersException;
import co.com.merdadolibre.challenge.domain.ReportSatellites;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.domain.services.level3.Request;
import co.com.merdadolibre.challenge.commons.exceptions.DistanceValueException;
import co.com.merdadolibre.challenge.commons.exceptions.MessageNullException;
import co.com.merdadolibre.challenge.level3.services.IMessageServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(value="Decodificacion y consulta del mensaje interceptado por los satelites de la alianza rebelde")
public class ComunicationL3 {

    private final IMessageServices services;

    @ApiOperation(value = "Guardado del mensaje reportado por cada satelite", response = co.com.merdadolibre.challenge.domain.services.level3.Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Informacion guardada exitosamente"),
            @ApiResponse(code = 204, message = "Mensaje ya existe"),
            @ApiResponse(code = 400, message = "Error en la informacion enviada"),
            @ApiResponse(code = 404, message = "Error al decodificar mensaje"),
            @ApiResponse(code = 500, message = "Error interno en el servidor")
    })
    @PostMapping("/topsecret_split/{name}")
    public ResponseEntity<co.com.merdadolibre.challenge.domain.services.level3.Response> decodePost(@PathVariable("name") String name,
                                                                                                    @RequestBody co.com.merdadolibre.challenge.domain.services.level3.Request request) {
        ReportSatellites satelite = new ReportSatellites();
        satelite.setName(name);
        satelite.setDistance(request.getDistance());
        satelite.setMessage(request.getMessage());

        List<ReportSatellites> satellites = new ArrayList<>();
        satellites.add(satelite);

        co.com.merdadolibre.challenge.domain.services.level2.Request data = new co.com.merdadolibre.challenge.domain.services.level2.Request();
        data.setSatellites(satellites);

        co.com.merdadolibre.challenge.domain.services.level3.Response response = this.services.saveReport(data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Guardado del mensaje reportado por cada satelite", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Informacion consultada exitosamente"),
            @ApiResponse(code = 400, message = "Error en la informacion enviada"),
            @ApiResponse(code = 404, message = "Error al decodificar mensaje"),
            @ApiResponse(code = 500, message = "Error interno en el servidor")
    })
    @GetMapping("/topsecret_split")
    public ResponseEntity<Response> decodeGet(@RequestParam String id) {
        if (id.isEmpty()) throw new ParametersException("Verify your message id...");

        Response response = this.services.getReport(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void validation(Request data) {
        if (data.getDistance() == 0) throw new DistanceValueException("The ship distance can be cero (0)!");
        if (data.getMessage() == null) throw new MessageNullException("The message can not be null");
    }

}
