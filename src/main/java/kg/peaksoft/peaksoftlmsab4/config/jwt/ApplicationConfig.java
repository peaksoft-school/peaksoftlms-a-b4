package kg.peaksoft.peaksoftlmsab4.config.jwt;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
