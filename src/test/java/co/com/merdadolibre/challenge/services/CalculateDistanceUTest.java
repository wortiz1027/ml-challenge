package co.com.merdadolibre.challenge.services;

import co.com.merdadolibre.challenge.commons.services.CalculatePosition;
import co.com.merdadolibre.challenge.commons.services.contracts.IPosition;
import co.com.merdadolibre.challenge.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
class CalculateDistanceUTest {

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