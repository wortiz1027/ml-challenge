package co.com.merdadolibre.challenge.configurations;

import co.com.merdadolibre.challenge.services.CalculateDistance;
import co.com.merdadolibre.challenge.services.DecodeMessage;
import co.com.merdadolibre.challenge.services.contracts.IDecode;
import co.com.merdadolibre.challenge.services.contracts.IDistance;
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
    @Qualifier("distance")
    public IDistance distance() {
        return new CalculateDistance();
    }

}
