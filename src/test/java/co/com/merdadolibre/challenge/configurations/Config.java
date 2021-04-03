package co.com.merdadolibre.challenge.configurations;

import co.com.merdadolibre.challenge.services.CalculateDistance;
import co.com.merdadolibre.challenge.services.DecodeMessage;
import co.com.merdadolibre.challenge.services.contracts.IDecode;
import co.com.merdadolibre.challenge.services.contracts.IDistance;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public IDistance distance() {
        return new CalculateDistance();
    }

}
