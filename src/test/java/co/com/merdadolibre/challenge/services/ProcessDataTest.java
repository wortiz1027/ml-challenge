package co.com.merdadolibre.challenge.services;

import co.com.merdadolibre.challenge.commons.services.contracts.IDecode;
import co.com.merdadolibre.challenge.commons.services.contracts.IPosition;
import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.domain.ReportSatellites;
import co.com.merdadolibre.challenge.domain.services.Response;
import co.com.merdadolibre.challenge.domain.services.level2.Request;
import co.com.merdadolibre.challenge.commons.exceptions.MessageNotDecodeException;
import co.com.merdadolibre.challenge.level2.services.ProcessData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ProcessDataTest {

    @Mock
    private IDecode decode;

    @Mock
    private IPosition position;

    @InjectMocks
    private ProcessData undertest;

    @BeforeEach
    public void setUp() {
        Position pos = new Position();
        pos.setX(-487.3f);
        pos.setY(1557.0f);

        String message = "este es un mensaje secreto";

        when(decode.getMessage(any())).thenReturn(message);
        when(position.getLocation(any())).thenReturn(pos);
    }

    @Test
    public void itShouldGetPositionAndMessage() {
        String expected = "este es un mensaje secreto";
        float xExpected = -487.3f;
        float yExpected = 1557.0f;

        // Given
        Request data = new Request();
        data.setSatellites(data());

        // When
        Response result = undertest.processSatellitesData(data);

        // Then
        assertThat(result.getPosition().getX()).isEqualTo(xExpected);
        assertThat(result.getPosition().getY()).isEqualTo(yExpected);
        assertThat(result.getMessage()).isEqualTo(expected);

        verify(position).getLocation(any());
        verify(decode).getMessage(any());
    }

    @Test
    public void itShouldThrownMessageNotDecodeException() {
        // Given
        Request data = new Request();
        data.setSatellites(data());

        // When
        given(decode.getMessage(any())).willReturn("");

        // Then
        assertThatThrownBy(() -> undertest.processSatellitesData(data))
                                          .isInstanceOf(MessageNotDecodeException.class)
                                          .hasMessageContaining("Exception decoding the message...");
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