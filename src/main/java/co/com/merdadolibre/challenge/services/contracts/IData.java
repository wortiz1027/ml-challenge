package co.com.merdadolibre.challenge.services.contracts;

import co.com.merdadolibre.challenge.domain.services.level2.Request;
import co.com.merdadolibre.challenge.domain.services.Response;

public interface IData {

    Response processSatellitesData(Request data);

}
