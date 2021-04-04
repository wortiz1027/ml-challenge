package co.com.merdadolibre.challenge.level3.infraestructure.repository;

import co.com.merdadolibre.challenge.domain.Message;
import co.com.merdadolibre.challenge.domain.Status;
import co.com.merdadolibre.challenge.domain.services.level3.Response;
import co.com.merdadolibre.challenge.level3.infraestructure.repository.mappers.MessageRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor
public class ComunicationRepositoryImpl implements ComunicationRepository {

    private final JdbcTemplate template;

    @Override
    public Optional<List<Message>> findById(String id) {
        try {
            String sql = "SELECT * " +
                         "FROM TBL_MESSAGE " +
                         "WHERE MATCH(CORRELATION_ID, MESSAGE) AGAINST ( ? IN NATURAL LANGUAGE MODE ) " +
                         "ORDER BY SEQUENCE_ID ASC ";

            List<Message> messages = this.template.query(sql, new Object[] { id }, new MessageRowMapper());

           return Optional.of(messages);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> findByCorrelationId() {
        return Optional.empty();
    }

    @Override
    public CompletableFuture<String> create(Message message) {
        try {
            //if (findById(vendor.getIdProvider()).isPresent()) return CompletableFuture.completedFuture(Status.EXIST.name());

            String sql = "INSERT INTO TBL_MESSAGE (ID_MESSAGE, " +
                                                    "CORRELATION_ID, " +
                                                    "NAME_SATELLITE, " +
                                                    "DISTANCE, " +
                                                    "MESSAGE " +
                                                    "SEQUENCE_ID" +
                                                    "VALUES (?,?,?,?,?,?)";

            template.update(sql,
                    message.getId(),
                    message.getCorrelation(),
                    message.getName(),
                    message.getDistance(),
                    message.getMessage(),
                    message.getSequence());

            return CompletableFuture.completedFuture(Status.CREATED.name());
        } catch(Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(Status.ERROR.name());
        }
    }
}