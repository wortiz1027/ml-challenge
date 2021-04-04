package co.com.merdadolibre.challenge.level3.services;

import co.com.merdadolibre.challenge.commons.exceptions.MessageNotDecodeException;
import co.com.merdadolibre.challenge.commons.exceptions.MessageNullException;
import co.com.merdadolibre.challenge.commons.services.contracts.IDecode;
import co.com.merdadolibre.challenge.commons.services.contracts.IPosition;
import co.com.merdadolibre.challenge.domain.Message;
import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.level3.infraestructure.repository.ComunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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
    public CompletableFuture<Response> getReport(String id) {
        Response response = new Response();
            Optional<List<Message>> messages = this.repository.findById(id);

            if (!messages.isPresent()) throw new MessageNullException(String.format("There is no information about message with code: [%s]", id));
            if (messages.get().size() < 3) throw new MessageNullException(String.format("Is no posible decode message with code: [%s]", id));

            Position position = this.position.getLocation(buildDistance(messages));
            List<String[]> msgs = messages.get().stream().map(row -> row.getMessage().split(",")).collect(Collectors.toList());

            String message = decode.getMessage(msgs);

            if (message.isEmpty()) throw new MessageNotDecodeException(String.format("Exception decoding the message whit code: [%s]...", id));

            response.setPosition(position);
            response.setMessage(message);

            return CompletableFuture.completedFuture(response);
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