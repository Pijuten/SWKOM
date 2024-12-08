package org.openapitools.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "org.openapitools.repositories.elasticsearch"
)
public class ElasticSearchConfig {
}