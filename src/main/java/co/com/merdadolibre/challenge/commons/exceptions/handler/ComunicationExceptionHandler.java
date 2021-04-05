package co.com.merdadolibre.challenge.commons.exceptions.handler;

import co.com.merdadolibre.challenge.commons.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ComunicationExceptionHandler {

    @ExceptionHandler(MessageNotDecodeException.class)
    public ResponseEntity<ComunicationResponseException> messageError(MessageNotDecodeException ex) {
        ComunicationResponseException response = new ComunicationResponseException();
        response.setCode("NOT_FOUND");
        response.setMessage(ex.getMessage());
        response.setTime(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PositionNotFoundException.class)
    public ResponseEntity<ComunicationResponseException> positionError(PositionNotFoundException ex) {
        ComunicationResponseException response = new ComunicationResponseException();
        response.setCode("NOT_FOUND");
        response.setMessage(ex.getMessage());
        response.setTime(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DistanceValueException.class)
    public ResponseEntity<ComunicationResponseException> distanceError(DistanceValueException ex) {
        ComunicationResponseException response = new ComunicationResponseException();
        response.setCode("BAD_REQUEST");
        response.setMessage(ex.getMessage());
        response.setTime(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageNullException.class)
    public ResponseEntity<ComunicationResponseException> messageError(MessageNullException ex) {
        ComunicationResponseException response = new ComunicationResponseException();
        response.setCode("NOT_FOUND");
        response.setMessage(ex.getMessage());
        response.setTime(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SateliteNameEmptyOrNullException.class)
    public ResponseEntity<ComunicationResponseException> sateliteError(SateliteNameEmptyOrNullException ex) {
        ComunicationResponseException response = new ComunicationResponseException();
        response.setCode("BAD_REQUEST");
        response.setMessage(ex.getMessage());
        response.setTime(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParametersException.class)
    public ResponseEntity<ComunicationResponseException> messageError(ParametersException ex) {
        ComunicationResponseException response = new ComunicationResponseException();
        response.setCode("BAD_REQUEST");
        response.setMessage(ex.getMessage());
        response.setTime(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SatelliteNameAlreadyExistsException.class)
    public ResponseEntity<ComunicationResponseException> sateliteNameError(SatelliteNameAlreadyExistsException ex) {
        ComunicationResponseException response = new ComunicationResponseException();
        response.setCode("ALREADY_REPORTED");
        response.setMessage(ex.getMessage());
        response.setTime(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ComunicationResponseException> serverError(ServerException ex) {
        ComunicationResponseException response = new ComunicationResponseException();
        response.setCode("INTERNAL_SERVER_ERROR");
        response.setMessage(ex.getMessage());
        response.setTime(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}