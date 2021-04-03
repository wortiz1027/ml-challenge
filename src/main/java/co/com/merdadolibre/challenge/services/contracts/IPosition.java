package co.com.merdadolibre.challenge.services.contracts;

import co.com.merdadolibre.challenge.domain.Position;

public interface IPosition {

    Position getLocation(float[] distances);

}
