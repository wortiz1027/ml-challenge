package co.com.merdadolibre.challenge.level3.infraestructure.repository;

import co.com.merdadolibre.challenge.domain.Status;
import co.com.merdadolibre.challenge.domain.services.level3.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor
public class ComunicationRepositoryImpl implements ComunicationRepository {

    private final JdbcTemplate template;


    @Override
    public Optional<Response> findById(String id) {
        try {
            String sql = "SELECT * " +
                         "FROM TBL_MESSAGE WHERE ID_MESSAGE = ?";
            return template.queryForObject(sql,
                                            new Object[]{id},
                                            (rs, rowNum) ->
                                                    Optional.of(new Vendor(
                                                                    rs.getString("ID_MESSAGE"),
                                                                    rs.getString("NAME_SATELLITE"),
                                                                    rs.getString("DISTANCE"),
                                                                    rs.getString("MESSAGE"),
                                                                    rs.getString("CORRELATION_ID")
                                                    ))
                                    );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> findByCorrelationId() {
        return Optional.empty();
    }

    @Override
    public CompletableFuture<String> create() {
        try {
            //if (findById(vendor.getIdProvider()).isPresent()) return CompletableFuture.completedFuture(Status.EXIST.name());

            String sql = "INSERT INTO TBL_MESSAGE (ID_MESSAGE, " +
                                                    "NAME_SATELLITE, " +
                                                    "DISTANCE, " +
                                                    "MESSAGE, " +
                                                    "CORRELATION_ID " +
                                                    "VALUES (?,?,?,?,?)";

            template.update(sql,
                    );

            return CompletableFuture.completedFuture(Status.CREATED.name());
        } catch(Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(Status.ERROR.name());
        }
    }
}
