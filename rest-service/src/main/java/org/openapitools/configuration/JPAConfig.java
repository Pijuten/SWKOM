package org.openapitools.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.openapitools.repositories.jpa"
)
public class JPAConfig {
}