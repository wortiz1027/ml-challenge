package co.com.merdadolibre.challenge.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Estructura con los campos detallados de la informacion que reportan los satelites")
public class ReportSatellites implements java.io.Serializable {

    @ApiModelProperty(notes = "Campo con el nombre del satelite")
    private String name;

    @ApiModelProperty(notes = "Campo con la informacion de la distancia relativa del satelite a la nave enemiga")
    private float distance;

    @ApiModelProperty(notes = "Campo con la informacion del mensaje interceptado por el satelite")
    private String[] message;

}
