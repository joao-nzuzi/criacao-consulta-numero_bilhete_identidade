package nzuzi.joao.criacao_consulta_numero_bi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                ;
    }
}
