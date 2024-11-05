package com.farm.base.notification_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfigs {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("notification-service doc")
                        .version("1.0")
                        .description("Farm Management")
                        .contact(new Contact()
                                .name("javenock")
                                .email("test45@gmail.com")
                                .url("https://www.test.app")));
    }
}
