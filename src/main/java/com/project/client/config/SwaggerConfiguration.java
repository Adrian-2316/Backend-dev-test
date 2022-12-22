package com.project.client.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  /**
   * Generate an API with information of it.
   *
   * @return OpenAPI
   */
  public OpenAPI openApi() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Client API")
                .description("Client API")
                .version("v1.0")
                .contact(null)
                .termsOfService("TOC")
                .license(new License().name("License").url("#")));
  }

  /**
   * Generate a group which you can use the distinct endpoints of the class specified.
   *
   * @return GroupedOpenApi.
   */
  @Bean
  public GroupedOpenApi client() {
    return GroupedOpenApi.builder()
        .group("Client")
        .packagesToScan("com.project.client.content")
        .build();
  }
}
