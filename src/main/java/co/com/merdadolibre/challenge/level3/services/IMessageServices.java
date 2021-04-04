package co.com.merdadolibre.challenge.level3.services;

import co.com.merdadolibre.challenge.domain.services.Response;

import java.util.concurrent.CompletableFuture;

public interface IMessageServices {

    Response getReport(String id);

}
