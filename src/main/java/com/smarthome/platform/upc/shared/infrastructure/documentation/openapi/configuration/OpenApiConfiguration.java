package com.smarthome.platform.upc.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI platformOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("Smart Home Dashboard")
                        .description("API for Smart Home Dashboard services")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")));

        return openApi;
    }
}