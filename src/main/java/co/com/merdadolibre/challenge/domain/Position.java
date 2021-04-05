package co.com.merdadolibre.challenge.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Estructura con la informacion de las coordenadas X,Y")
public class Position implements java.io.Serializable {

    @ApiModelProperty(notes = "Campo que almacena las coordenadas X")
    private float x;

    @ApiModelProperty(notes = "Campo que almacena las coordenadas Y")
    private float y;

}
