package co.com.merdadolibre.challenge.configurations;

import co.com.merdadolibre.challenge.services.CalculatePosition;
import co.com.merdadolibre.challenge.services.DecodeMessage;
import co.com.merdadolibre.challenge.services.contracts.IDecode;
import co.com.merdadolibre.challenge.services.contracts.IPosition;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@TestConfiguration
public class Config {

    @Bean
    @Primary
    public IDecode decode() {
        return new DecodeMessage();
    }

    @Bean
    @Primary
    public IPosition distance() {
        return new CalculatePosition();
    }

}
