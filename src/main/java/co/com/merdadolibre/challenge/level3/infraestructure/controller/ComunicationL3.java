package co.com.merdadolibre.challenge.level3.infraestructure.controller;

import co.com.merdadolibre.challenge.commons.exceptions.ParametersException;
import co.com.merdadolibre.challenge.domain.ReportSatellites;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.domain.services.level2.RequestL2;
import co.com.merdadolibre.challenge.domain.services.level3.RequestL3;
import co.com.merdadolibre.challenge.commons.exceptions.DistanceValueException;
import co.com.merdadolibre.challenge.commons.exceptions.MessageNullException;
import co.com.merdadolibre.challenge.domain.services.level3.ResponseL3;
import co.com.merdadolibre.challenge.level3.services.IMessageServices;
import io.swagger.annotations.*;
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

    @ApiOperation(value = "Guardado del mensaje reportado por cada satelite", response = ResponseL3.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Informacion guardada exitosamente"),
            @ApiResponse(code = 204, message = "Mensaje ya existe"),
            @ApiResponse(code = 400, message = "Error en la informacion enviada"),
            @ApiResponse(code = 404, message = "Error al decodificar mensaje"),
            @ApiResponse(code = 500, message = "Error interno en el servidor")
    })
    @PostMapping("/topsecret_split/{name}")
    public ResponseEntity<ResponseL3> decodePost(@PathVariable("name") String name,
                                                 @RequestBody RequestL3 request) {
        ReportSatellites satelite = new ReportSatellites();
        satelite.setName(name);
        satelite.setDistance(request.getDistance());
        satelite.setMessage(request.getMessage());

        List<ReportSatellites> satellites = new ArrayList<>();
        satellites.add(satelite);

        RequestL2 data = new RequestL2();
        data.setSatellites(satellites);

        ResponseL3 responseL3 = this.services.saveReport(data);

        return new ResponseEntity<>(responseL3, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Guardado del mensaje reportado por cada satelite", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Informacion consultada exitosamente"),
            @ApiResponse(code = 400, message = "Error en la informacion enviada"),
            @ApiResponse(code = 404, message = "Error al decodificar mensaje"),
            @ApiResponse(code = 500, message = "Error interno en el servidor")
    })
    @GetMapping("/topsecret_split")
    public ResponseEntity<Response> decodeGet(@ApiParam(name =  "id",
                                                        type = "String",
                                                        value = "correlation id",
                                                        example = "e0171462-0b8f-4384-a837-fd2def16a969",
                                                        required = true) @RequestParam String id) {
        if (id.isEmpty()) throw new ParametersException("Verify your message id...");

        Response response = this.services.getReport(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void validation(RequestL3 data) {
        if (data.getDistance() == 0) throw new DistanceValueException("The ship distance can be cero (0)!");
        if (data.getMessage() == null) throw new MessageNullException("The message can not be null");
    }

}
