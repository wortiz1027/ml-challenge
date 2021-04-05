package co.com.merdadolibre.challenge.level3.infraestructure.repository;

import co.com.merdadolibre.challenge.domain.Correlation;
import co.com.merdadolibre.challenge.domain.Message;
import co.com.merdadolibre.challenge.domain.Status;
import co.com.merdadolibre.challenge.level3.infraestructure.repository.mappers.MessageRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<Correlation> findLastMessage() {
        try {
            String sql = "SELECT count(*) AS MINIMUN, CORRELATION_ID " +
                         "FROM TBL_MESSAGE " +
                         "GROUP BY CORRELATION_ID ORDER BY MINIMUN ASC LIMIT 1";

            return this.template.queryForObject(sql, (rs, rowNum) ->
                                                        Optional.of(new Correlation(rs.getInt("MINIMUN"),
                                                                                    rs.getString("CORRELATION_ID"))
                                                                    )
                                                );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public String create(Message message) {
        try {
            String sql = "INSERT INTO TBL_MESSAGE (ID_MESSAGE, " +
                                                    "CORRELATION_ID, " +
                                                    "NAME_SATELLITE, " +
                                                    "DISTANCE, " +
                                                    "MESSAGE, " +
                                                    "SEQUENCE_ID) " +
                                                    "VALUES (?,?,?,?,?,?)";

            template.update(sql,
                    message.getId(),
                    message.getCorrelation(),
                    message.getName(),
                    message.getDistance(),
                    message.getMessage(),
                    message.getSequence());

            return Status.CREATED.name();
        } catch(Exception e) {
            return Status.ERROR.name();
        }
    }
}