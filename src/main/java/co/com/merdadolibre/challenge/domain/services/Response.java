package co.com.merdadolibre.challenge.domain.services;

import co.com.merdadolibre.challenge.domain.Position;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Estructura con la respuesta detallada de la ubicacion de la nave enemiga y el mensaje decodificado")
public class Response implements java.io.Serializable {

    @ApiModelProperty(notes = "Campo que almacena las coordenadas de la nave enemiga")
    private Position position;

    @ApiModelProperty(notes = "Campo con el mensaje interceptado decodificado")
    private String message;

}
