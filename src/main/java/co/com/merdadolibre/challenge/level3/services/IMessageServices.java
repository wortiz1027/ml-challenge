package co.com.merdadolibre.challenge.level3.services;

import co.com.merdadolibre.challenge.domain.services.Response;

import co.com.merdadolibre.challenge.domain.services.level2.RequestL2;
import co.com.merdadolibre.challenge.domain.services.level3.ResponseL3;

public interface IMessageServices {

    ResponseL3 saveReport(RequestL2 data);
    Response getReport(String id);

}
