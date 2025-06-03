package deploy.project.cloud_parking.Config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cloud Parking API")
                        .description("API para gerenciamento de estacionamento em nuvem")
                        .version("1.0")
                        .contact(new Contact()
                                .name("João Antonio")
                                .email("joaocoelhoreis@exemplo.com")
                                .url("https://www.linkedin.com/in/joão-antônio-coelho-dos-reis-b0385728b/")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Link do Repositório do Projeto.")
                        .url("https://github.com/JoaoAntonioCdR/cloud-parking")
                );
    }
}

