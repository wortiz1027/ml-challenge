package co.com.merdadolibre.challenge.services;

import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.domain.ReportSatellites;
import co.com.merdadolibre.challenge.domain.services.level2.Request;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.exceptions.DistanceValueException;
import co.com.merdadolibre.challenge.exceptions.MessageNotDecodeException;
import co.com.merdadolibre.challenge.services.contracts.IData;
import co.com.merdadolibre.challenge.services.contracts.IDecode;
import co.com.merdadolibre.challenge.services.contracts.IPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessData implements IData {

    @Qualifier("decode")
    private final IDecode decode;

    @Qualifier("position")
    private final IPosition position;

    @Override
    public Response processSatellitesData(Request data) {

        if (data.getSatellites().size() < 3) throw new DistanceValueException("Without distance can not calculate transmitter position...");

        Response response = new Response();
        Position position = this.position.getLocation(build(data));
        List<String[]> messages = data.getSatellites()
                                      .stream()
                                      .map(ReportSatellites::getMessage)
                                      .collect(Collectors.toList());

        String message = decode.getMessage(messages);

        if (message.isEmpty()) throw new MessageNotDecodeException("Exception decoding the message...");

        response.setPosition(position);
        response.setMessage(message);

        return response;
    }

    public float[] build(Request data) {
        int index = 0;
        float[] distances = new float[data.getSatellites().size()];

        for (ReportSatellites rs : data.getSatellites()) {
            distances[index] = rs.getDistance();
            index++;
        }

        return distances;
    }
}
