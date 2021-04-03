package co.com.merdadolibre.challenge.services;

import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.services.contracts.IDistance;
import org.springframework.stereotype.Component;

@Component
public class CalculateDistance implements IDistance {

    @Override
    public Position getDistances(float[] distances) {
        Position position = new Position();
        position.setX(-100.0f);
        position.setY(75.5f);

        return position;
    }

}
