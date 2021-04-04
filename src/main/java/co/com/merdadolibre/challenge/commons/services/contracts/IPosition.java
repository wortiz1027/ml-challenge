package co.com.merdadolibre.challenge.commons.services.contracts;

import co.com.merdadolibre.challenge.domain.Position;

public interface IPosition {

    Position getLocation(float[] distances);

}
