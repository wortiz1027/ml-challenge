package co.com.merdadolibre.challenge.services;

import co.com.merdadolibre.challenge.domain.Position;
import co.com.merdadolibre.challenge.services.contracts.IPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@ActiveProfiles("test")
class CalculateDistanceTest {

    //@Autowired
    IPosition underTest;

    @BeforeEach
    void SetUp() {
        underTest = new CalculatePosition();
    }

    @Test
    void itShouldReturnEnemyShipPosition() {
        // Data
        Position expected = new Position();
        expected.setX((float) -487.3);
        expected.setY((float) 1557.0);

        // given
        float[] distances = {100.0f, 115.5f, 142.7f};

        // when
        Position result = underTest.getLocation(distances);

        // then
        assertThat(result.getX()).isEqualTo(expected.getX());
        assertThat(result.getY()).isEqualTo(expected.getY());
    }
}