package at.fhtw.ocrservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "at.fhtw.ocrservice.repositories.jpa"
)
public class JPAConfig {
}