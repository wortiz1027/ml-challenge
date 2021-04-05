package co.com.merdadolibre.challenge.level3.infraestructure.repository;

import co.com.merdadolibre.challenge.domain.Correlation;
import co.com.merdadolibre.challenge.domain.Message;

import java.util.List;
import java.util.Optional;

public interface ComunicationRepository {

    Optional<List<Message>> findById(String id);
    Optional<Correlation> findLastMessage();
    String create(Message message);

}