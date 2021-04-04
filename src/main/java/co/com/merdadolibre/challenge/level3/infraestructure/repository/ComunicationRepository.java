package co.com.merdadolibre.challenge.level3.infraestructure.repository;

import co.com.merdadolibre.challenge.domain.Message;
import co.com.merdadolibre.challenge.domain.services.level3.Response;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ComunicationRepository {

    Optional<List<Message>> findById(String id);
    Optional<String> findByCorrelationId();
    CompletableFuture<String> create(Message message);

}