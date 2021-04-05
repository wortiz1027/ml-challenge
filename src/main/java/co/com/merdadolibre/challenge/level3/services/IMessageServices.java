package co.com.merdadolibre.challenge.level3.services;

import co.com.merdadolibre.challenge.domain.services.Response;

import co.com.merdadolibre.challenge.domain.services.level2.Request;

public interface IMessageServices {

    co.com.merdadolibre.challenge.domain.services.level3.Response saveReport(Request data);
    Response getReport(String id);

}
