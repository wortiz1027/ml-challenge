package co.com.merdadolibre.challenge.level3.infraestructure.repository;

import co.com.merdadolibre.challenge.domain.Correlation;
import co.com.merdadolibre.challenge.domain.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ComunicationRepositoryUTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    ComunicationRepository undertest;

    @BeforeEach
    void SetUp() {
        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setId("4e4036ed-03ae-4146-9733-bf40ed9113c2");
        message.setCorrelation("e0171462-0b8f-4384-a837-fd2def16a969");
        message.setName("kenobi");
        message.setDistance(100.0f);
        message.setMessage("este,,,mensaje,");
        message.setSequence("1-1");

        messages.add(message);

        lenient().doReturn(Optional.of(messages)).when(undertest).findById("e0171462-0b8f-4384-a837-fd2def16a969");

        Correlation correlation = new Correlation(3, "e0171462-0b8f-4384-a837-fd2def16a969");
        lenient().doReturn(Optional.of(correlation)).when(undertest).findLastMessage();

        lenient().doReturn("CREATED").when(undertest).create(message);
    }

    @Test
    void findById() {
       Optional<List<Message>> message = undertest.findById("e0171462-0b8f-4384-a837-fd2def16a969");
       assertThat(message.get().size()).isEqualTo(1);
    }

    @Test
    void findLastMessage() {
        Optional<Correlation> correlation = undertest.findLastMessage();
        assertThat(correlation.get().getMinimun()).isEqualTo(3);
        assertThat(correlation.get().getCorrelation()).isEqualTo("e0171462-0b8f-4384-a837-fd2def16a969");
    }

    @Test
    void create() throws ExecutionException, InterruptedException {
        Message message = new Message();
        message.setId("4e4036ed-03ae-4146-9733-bf40ed9113c2");
        message.setCorrelation("e0171462-0b8f-4384-a837-fd2def16a969");
        message.setName("kenobi");
        message.setDistance(100.0f);
        message.setMessage("este,,,mensaje,");
        message.setSequence("1-1");

        String response = undertest.create(message);
        assertThat(response).isEqualTo("CREATED");
    }
}