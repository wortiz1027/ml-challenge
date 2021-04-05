package co.com.merdadolibre.challenge.level3.services;

import co.com.merdadolibre.challenge.commons.exceptions.MessageNotDecodeException;
import co.com.merdadolibre.challenge.commons.exceptions.MessageNullException;
import co.com.merdadolibre.challenge.commons.exceptions.SatelliteNameAlreadyExistsException;
import co.com.merdadolibre.challenge.commons.services.contracts.IDecode;
import co.com.merdadolibre.challenge.commons.services.contracts.IPosition;
import co.com.merdadolibre.challenge.domain.Correlation;
import co.com.merdadolibre.challenge.domain.Message;
import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.domain.Status;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.domain.services.level2.RequestL2;
import co.com.merdadolibre.challenge.domain.services.level3.ResponseL3;
import co.com.merdadolibre.challenge.level3.infraestructure.repository.ComunicationRepository;
import co.com.merdadolibre.challenge.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServices implements IMessageServices {

    @Qualifier("decode")
    private final IDecode decode;

    @Qualifier("position")
    private final IPosition position;

    private final ComunicationRepository repository;

    @Override
    public ResponseL3 saveReport(RequestL2 data) {
        ResponseL3 responseL3 = new ResponseL3();
        Optional<Correlation> correlation = this.repository.findLastMessage();

        if (!correlation.isPresent())
            throw new MessageNotDecodeException(String.format("There is an error saving message from satellite: [%s]", data.getSatellites().get(0).getName()));

        int sequence = 0;
        String corrId = "";

        if (correlation.get().getMinimun() < 3) {
            corrId   = correlation.get().getCorrelation();
            sequence = correlation.get().getMinimun() + 1;
        }

        Optional<List<Message>> messages = this.repository.findById(corrId);

        validateSatelliteMEssage(messages, data.getSatellites().get(0).getName());

        if (correlation.get().getMinimun() == 3) {
            corrId   = Utilities.getId();
            sequence = 1;
        }

        final String formatMessage = String.join(",", data.getSatellites().get(0).getMessage());;

        Message message = new Message();
        message.setId(Utilities.getId());
        message.setCorrelation(corrId);
        message.setName(data.getSatellites().get(0).getName());
        message.setDistance(data.getSatellites().get(0).getDistance());
        message.setMessage(formatMessage);
        message.setSequence(String.format("1-%s", sequence));

        this.repository.create(message);
        responseL3.setMessage(String.format("Operation Status: %s", Status.CREATED.name()));

        return responseL3;
    }

    private void validateSatelliteMEssage(Optional<List<Message>> messages, String name) {
        if (messages.isPresent()) {
            boolean isNameExists = messages.get().stream().anyMatch(n -> n.getName().equalsIgnoreCase(name));

            if (isNameExists) throw new SatelliteNameAlreadyExistsException(String.format("Already message reported by satellite: %s", name));
        }
    }

    @Override
    public Response getReport(String id) {
        Response response = new Response();
        Optional<List<Message>> messages = this.repository.findById(id);

        if (!messages.isPresent()) throw new MessageNullException(String.format("There is no information about message with code: [%s]", id));
        if (messages.get().size() < 3) throw new MessageNullException(String.format("Is no posible decode message with code: [%s]", id));

        Position position = this.position.getLocation(buildDistance(messages));
        List<String[]> msgs = messages.get().stream().map(row -> row.getMessage().split(",", -1)).collect(Collectors.toList());

        String message = decode.getMessage(msgs);

        if (message.isEmpty()) throw new MessageNotDecodeException(String.format("Exception decoding the message whit code: [%s]...", id));

        response.setPosition(position);
        response.setMessage(message);

        return response;
    }

    public float[] buildDistance(Optional<List<Message>> data) {
        int index = 0;
        float[] distances = new float[data.get().size()];

        for (Message row : data.get()) {
            distances[index] = (float) row.getDistance();
            index++;
        }

        return distances;
    }

}