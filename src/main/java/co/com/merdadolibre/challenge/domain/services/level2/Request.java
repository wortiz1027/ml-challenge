package co.com.merdadolibre.challenge.domain.services.level2;

import co.com.merdadolibre.challenge.domain.Satellites;
import lombok.Data;

import java.util.List;

@Data
public class Request implements java.io.Serializable {

    private List<Satellites> satellites;

}