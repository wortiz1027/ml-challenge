package co.com.merdadolibre.challenge.domain.services.level3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Estructura con la confirmacion de creacion del mensaje")
public class ResponseL3 implements java.io.Serializable {

    @ApiModelProperty(notes = "Campo que almacena lña respuesta de confirmacion de creacion del mensaje")
    private String message;

}
