package co.com.merdadolibre.challenge.level3;

import co.com.merdadolibre.challenge.domain.services.level3.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.core.MediaType;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("itest")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ComunicationLevel3ITest {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mvc;

    @Autowired
    ComunicationLevel3ITest(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    void itShouldGetMessageInformation() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "e0171462-0b8f-4384-a837-fd2def16a969");


        mvc.perform( MockMvcRequestBuilders
                .get("/api/v1/topsecret_split")
                .params(requestParams)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
    }

    @Test
    void itShouldSaveMessageInformation() throws Exception {
        String name = "kenobi";
        Request data = new Request();
        data.setDistance(190.5f);
        data.setMessage(new String[] {"este","","","mensaje","", "", ""});

        mvc.perform( MockMvcRequestBuilders
                .post("/api/v1/topsecret_split/{name}", name)
                .content(asJsonString(data))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", containsStringIgnoringCase("CREATED")));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}