package co.com.merdadolibre.challenge.commons.services;

import co.com.merdadolibre.challenge.commons.services.contracts.IPosition;
import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.domain.Satellite;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class CalculatePosition implements IPosition {

    private final Satellite kenobi = new Satellite();
    private final Satellite skywalker = new Satellite();
    private final Satellite sato = new Satellite();

    @Override
    public Position getLocation(final float[] distances) {
        initPositions();

        Position position = new Position();

        double[] pointKenobi    = {kenobi.getPosition().getX(), kenobi.getPosition().getY()};
        double[] pointSkywalker = {skywalker.getPosition().getX(), skywalker.getPosition().getY()};
        double[] pointSato      = {sato.getPosition().getX(), sato.getPosition().getY()};

        final double skke    = IntStream.range(0, pointKenobi.length)
                                        .mapToDouble(index -> Math.pow(pointSkywalker[index] - pointKenobi[index], 2))
                                        .sum();

        final double dist    = Math.sqrt(skke);

        final double[] axisX = IntStream.range(0, pointKenobi.length)
                                        .mapToDouble(index -> (pointSkywalker[index] - pointKenobi[index]) / (Math.sqrt(skke)))
                                        .toArray();

        final double[] sake  = IntStream.range(0, pointKenobi.length)
                                        .mapToDouble(index -> pointSato[index] - pointKenobi[index])
                                        .toArray();

        final double iVector = IntStream.range(0, axisX.length)
                                        .mapToDouble(index -> axisX[index] * sake[index])
                                        .sum();

        final double sakei   = IntStream.range(0, pointSato.length)
                                        .mapToDouble(index -> Math.pow(pointSato[index] - pointKenobi[index] - (axisX[index] * iVector), 2))
                                        .sum();

        final double[] axisY = IntStream.range(0, pointSato.length)
                                        .mapToDouble(index -> (pointSato[index] - pointKenobi[index] - (axisX[index] * iVector)) / Math.sqrt(sakei))
                                        .toArray();

        final double jVector = IntStream.range(0, axisY.length)
                                        .mapToDouble(index -> axisY[index] * sake[index])
                                        .sum();

        double xvalue = (Math.pow(distances[0], 2)  - Math.pow(distances[1], 2) + Math.pow(dist, 2)) / (2 * dist);
        double yvalue = ((Math.pow(distances[0], 2) - Math.pow(distances[2], 2) + Math.pow(iVector, 2) + Math.pow(jVector, 2)) / (2 * jVector)) - ((iVector / jVector) * xvalue);

        final double x = kenobi.getPosition().getX() + (axisX[0] * xvalue) + (axisY[0] * yvalue);
        final double y = kenobi.getPosition().getY() + (axisX[1] * xvalue) + (axisY[1] * yvalue);

        position.setX(format((float) x));
        position.setY(format((float) y));

        return position;
    }

    private void initPositions() {
        Position posKenobi = new Position();
        posKenobi.setX(-500f);
        posKenobi.setY(-200f);

        Position posSkywalker = new Position();
        posSkywalker.setX(100f);
        posSkywalker.setY(-100f);

        Position posSato = new Position();
        posSato.setX(500);
        posSato.setY(100);

        kenobi.setPosition(posKenobi);
        skywalker.setPosition(posSkywalker);
        sato.setPosition(posSato);
    }

    private float format(final float value) {
        final String FORMAT = "%.1f";
        return Float.parseFloat(String.format(java.util.Locale.US, FORMAT, value));
    }

}
