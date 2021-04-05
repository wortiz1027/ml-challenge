package co.com.merdadolibre.challenge.level3.infraestructure.repository.mappers;

import co.com.merdadolibre.challenge.domain.Correlation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CorrelationRowMapper implements RowMapper<Correlation> {

    @Override
    public Correlation mapRow(ResultSet rs, int i) throws SQLException {
        Correlation correlation = new Correlation();
        correlation.setMinimun(rs.getInt("MINIMUN"));
        correlation.setCorrelation(rs.getString("CORRELATION_ID"));

        return correlation;
    }
}