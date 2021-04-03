package co.com.merdadolibre.challenge.services;

import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.services.contracts.IDistance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CalculateDistanceTest {

    @Autowired
    IDistance underTest;

    @Test
    void itShouldReturnEnemyShipPosition() {
        // Data
        Position expected = new Position();
        expected.setX((float) -100.0);
        expected.setY((float) 75.5);

        // given
        float[] distances = {100.0f, 115.5f, 142.7f};

        // when
        Position result = underTest.getDistances(distances);

        // then
        assertThat(result.getX()).isEqualTo(expected.getX());
        assertThat(result.getY()).isEqualTo(expected.getY());
    }
}