package co.com.merdadolibre.challenge.domain.services.level3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Estructura con la informacion de la distancia y mensaje interceptado por cada satelite")
public class Request implements java.io.Serializable {

    @ApiModelProperty(notes = "Campo que almacena el valor de la distancia entre el satelite que reporta y la nave enemiga")
    private float distance;

    @ApiModelProperty(notes = "Campo que almacena el mensaje interceptado")
    private String[] message;

}
