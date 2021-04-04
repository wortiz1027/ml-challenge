package co.com.merdadolibre.challenge.commons.configurations;

import co.com.merdadolibre.challenge.commons.services.CalculatePosition;
import co.com.merdadolibre.challenge.commons.services.DecodeMessage;
import co.com.merdadolibre.challenge.commons.services.contracts.IDecode;
import co.com.merdadolibre.challenge.commons.services.contracts.IPosition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    @Qualifier("decode")
    public IDecode decode() {
        return new DecodeMessage();
    }

    @Bean
    @Qualifier("position")
    public IPosition position() {
        return new CalculatePosition();
    }

}
