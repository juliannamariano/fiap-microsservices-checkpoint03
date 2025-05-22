package br.com.fiap.checkpoint3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDoc() {
        return new OpenAPI()
                .info(new Info()
                        .title("Checkpoint 3 - API de Consultas")
                        .description("Documentação da API de Pacientes, Profissionais e Consultas")
                        .version("1.0"));
    }
}
