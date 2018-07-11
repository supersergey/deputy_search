package ua.kiev.supersergey.deputysearch.google_search_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

import static ua.kiev.supersergey.deputysearch.google_search_client.httpclient.WebClientLogger.logRequest;

@SpringBootApplication
@EnableJpaRepositories(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.dao")
@EntityScan(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.entity")
public class GoogleSearchClientApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GoogleSearchClientApp.class, args);
        GoogleSearchDirector googleSearchDirector = context.getBean(GoogleSearchDirector.class);
        googleSearchDirector.doSearch();
        context.close();
    }

    @Bean
    public WebClient createWebClient() {
        return WebClient.builder()
                .baseUrl("https://www.googleapis.com/customsearch/v1")
                .filter(logRequest())
                .build();
    }
}
