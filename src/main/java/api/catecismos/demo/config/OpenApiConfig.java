package api.catecismos.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI westminsterOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Westminster API")
                        .description("REST API for the Westminster Shorter Catechism, "
                                + "Westminster Larger Catechism and "
                                + "Westminster Confession of Faith.")
                        .version("1.0.0"));
    }
}
