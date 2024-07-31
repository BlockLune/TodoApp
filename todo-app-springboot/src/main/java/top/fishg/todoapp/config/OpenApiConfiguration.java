package top.fishg.todoapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
            .title("Todo App API")
            .description("A hands-on project to build a todo app.")
            .license(new License()
                .name("Mulan PubL v2")
                .url("https://github.com/BlockLune/TodoApp?tab=License-1-ov-file")
            )
        );
    }
}
