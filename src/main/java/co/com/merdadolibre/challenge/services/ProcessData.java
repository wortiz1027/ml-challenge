package co.com.merdadolibre.challenge.services;

import co.com.merdadolibre.challenge.domain.services.level2.Request;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.services.contracts.IData;
import co.com.merdadolibre.challenge.services.contracts.IDecode;
import co.com.merdadolibre.challenge.services.contracts.IDistance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessData implements IData {

    private final IDecode decode;

    private final IDistance distance;

    @Override
    public Response processSatellitesData(Request data) {
        return null;
    }
}
