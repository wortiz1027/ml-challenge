package co.com.merdadolibre.challenge.level3.infraestructure.repository.mappers;

import co.com.merdadolibre.challenge.domain.Message;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int i) throws SQLException {
        Message message = new Message();

        message.setId(rs.getString("ID_MESSAGE"));
        message.setCorrelation(rs.getString("CORRELATION_ID"));
        message.setName(rs.getString("NAME_SATELLITE"));
        message.setDistance(rs.getDouble("DISTANCE"));
        message.setMessage(rs.getString("MESSAGE"));
        message.setSequence(rs.getString("SEQUENCE_ID"));

        return message;
    }

}