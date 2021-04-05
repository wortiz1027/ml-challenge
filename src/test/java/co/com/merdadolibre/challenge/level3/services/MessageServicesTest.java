package co.com.merdadolibre.challenge.level3.services;

import co.com.merdadolibre.challenge.commons.services.contracts.IDecode;
import co.com.merdadolibre.challenge.commons.services.contracts.IPosition;
import co.com.merdadolibre.challenge.domain.Correlation;
import co.com.merdadolibre.challenge.domain.Message;
import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.domain.ReportSatellites;
import co.com.merdadolibre.challenge.domain.services.level3.Response;
import co.com.merdadolibre.challenge.level3.infraestructure.repository.ComunicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class MessageServicesTest {

    @Mock
    private IDecode decode;

    @Mock
    private IPosition position;

    @Mock
    ComunicationRepository repository;

    @InjectMocks
    private MessageServices undertest;

    @BeforeEach
    public void setUp() {
        String corrId = "e0171462-0b8f-4384-a837-fd2def16a969";

        List<Message> messages = new ArrayList<>();
        Message message1 = new Message();
        message1.setId("4e4036ed-03ae-4146-9733-bf40ed9113c2");
        message1.setCorrelation(corrId);
        message1.setName("kenobi");
        message1.setDistance(100.0f);
        message1.setMessage("este,,,mensaje,");
        message1.setSequence("1-1");

        Message message2 = new Message();
        message2.setId("4e4036ed-03ae-4146-9733-bf40ed9113c2");
        message2.setCorrelation(corrId);
        message2.setName("skywalker");
        message2.setDistance(100.0f);
        message2.setMessage("este,,,mensaje,");
        message2.setSequence("1-2");

        Message message3 = new Message();
        message3.setId("4e4036ed-03ae-4146-9733-bf40ed9113c2");
        message3.setCorrelation(corrId);
        message3.setName("sato");
        message3.setDistance(100.0f);
        message3.setMessage("este,,un,,");
        message3.setSequence("1-3");

        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        Position pos = new Position();
        pos.setX(-487.3f);
        pos.setY(1557.0f);

        String msg = "este es un mensaje secreto";

        Response response = new Response();
        response.setMessage(msg);

        Correlation correlation = new Correlation(3, corrId);

        co.com.merdadolibre.challenge.domain.services.Response rs = new co.com.merdadolibre.challenge.domain.services.Response();
        rs.setPosition(pos);
        rs.setMessage(msg);

        lenient().when(decode.getMessage(any())).thenReturn(msg);
        lenient().when(position.getLocation(any())).thenReturn(pos);
        lenient().when(repository.create(message1)).thenReturn(msg);
        lenient().when(repository.findLastMessage()).thenReturn(Optional.of(correlation));
        lenient().when(repository.findById(corrId)).thenReturn(Optional.of(messages));
    }

    @Test
    void saveReport() {
        String expected = "Operation Status: CREATED";

        co.com.merdadolibre.challenge.domain.services.level2.Request data = new co.com.merdadolibre.challenge.domain.services.level2.Request();
        data.setSatellites(data());

        Response result = undertest.saveReport(data);
        assertThat(result.getMessage()).isEqualTo(expected);
    }

    @Test
    void getReport() {
        String corrId = "e0171462-0b8f-4384-a837-fd2def16a969";
        String expected = "este es un mensaje secreto";
        float xExpected = -487.3f;
        float yExpected = 1557.0f;

        co.com.merdadolibre.challenge.domain.services.Response result = undertest.getReport(corrId);

        assertThat(result.getPosition().getX()).isEqualTo(xExpected);
        assertThat(result.getPosition().getY()).isEqualTo(yExpected);
        assertThat(result.getMessage()).isEqualTo(expected);
    }

    private List<ReportSatellites> data() {
        ReportSatellites kenobi = new ReportSatellites();
        kenobi.setName("kenobi");
        kenobi.setDistance(100.0f);
        kenobi.setMessage(new String[] {"este", "", "", "mensaje", ""});

        ReportSatellites skywalker = new ReportSatellites();
        skywalker.setName("skywalker");
        skywalker.setDistance(100.0f);
        skywalker.setMessage(new String[] {"", "es", "", "", "secreto"});

        ReportSatellites sato = new ReportSatellites();
        sato.setName("sato");
        sato.setDistance(100.0f);
        sato.setMessage(new String[] {"este", "", "un", "", ""});

        List<ReportSatellites> reports = new ArrayList<>();
        reports.add(kenobi);
        reports.add(skywalker);
        reports.add(sato);

        return reports;
    }

}