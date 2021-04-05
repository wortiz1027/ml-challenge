package co.com.merdadolibre.challenge.domain.services.level2;

import co.com.merdadolibre.challenge.domain.ReportSatellites;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Estructura para el manejo de la informacion del reporte de informacion de los satelites")
public class Request implements java.io.Serializable {

    @ApiModelProperty(notes = "Campo con el listado de informacion de cada uno de los satelites")
    private List<ReportSatellites> satellites;

}
