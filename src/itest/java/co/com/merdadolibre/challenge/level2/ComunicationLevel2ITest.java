package co.com.merdadolibre.challenge.level2;

import co.com.merdadolibre.challenge.domain.ReportSatellites;
import co.com.merdadolibre.challenge.domain.services.level2.Request;
import static org.hamcrest.CoreMatchers.is;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComunicationLevel2ITest {

    @Autowired
    private MockMvc mvc;

    @Test
    void itShouldReturnPositionAndMessage() throws Exception {
        Request data = new Request();
        data.setSatellites(data200());

        mvc.perform( MockMvcRequestBuilders
                        .post("/api/v1/topsecret")
                        .content(asJsonString(data))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.position").exists());
    }

    @Test
    void itShouldReturnBadRequestException() throws Exception {
        Request data = new Request();
        data.setSatellites(data400());

        mvc.perform( MockMvcRequestBuilders
                .post("/api/v1/topsecret")
                .content(asJsonString(data))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is("BAD_REQUEST")));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<ReportSatellites> data200() {
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

    private List<ReportSatellites> data400() {
        ReportSatellites kenobi = new ReportSatellites();
        kenobi.setName("kenobi");
        kenobi.setDistance(100.0f);
        kenobi.setMessage(null);

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
