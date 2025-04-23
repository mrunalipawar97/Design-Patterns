package edu.neu.csye7374.stockflow.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mrunalipawar
 */

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Stock Flow API Documentation",
        version = "1.0",
        description = "API documentation for Stock flow project",
        contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Shreyas Kothari", email = "spkothari96@gmail.com"),
        license = @io.swagger.v3.oas.annotations.info.License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
))
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("custom-api")
                .packagesToScan("com.designpattern.StockFlow.controller") // Specify your controllers' package
                .build();
    }
}


