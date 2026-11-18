package com.ritsuki.device.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  private static final String TOKEN = "token";

  @Bean
  public GroupedOpenApi productApi() {
    return GroupedOpenApi.builder()
            .group("SpringBoot-Swagger-API")
            .packagesToScan("com.ritsuki.device.controller")
            .build();
  }

  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI()
            .info(new Info()
                    .title("Home Automation Device API")
                    .description("REST API")
                    .version("1.0.0")
                    .license(new License()
                            .name("Apache License Version 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0")))
            .components(new Components().addSecuritySchemes(TOKEN, new SecurityScheme()
                    .type(SecurityScheme.Type.APIKEY)
                    .in(SecurityScheme.In.HEADER)
                    .name("Authorization")))
            .addSecurityItem(new SecurityRequirement().addList(TOKEN));
  }
}
