package co.com.merdadolibre.challenge.level3.infraestructure.repository;

import co.com.merdadolibre.challenge.domain.services.level3.Response;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ComunicationRepository {

    Optional<Response> findById(String id);
    Optional<String> findByCorrelationId();
    CompletableFuture<String> create();

}