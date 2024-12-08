package at.fhtw.ocrservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "at.fhtw.ocrservice.repositories.elasticsearch"
)
public class ElasticSearchConfig {
}