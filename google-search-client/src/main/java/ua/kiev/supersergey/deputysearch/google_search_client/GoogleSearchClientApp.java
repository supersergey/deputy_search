package ua.kiev.supersergey.deputysearch.google_search_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.dao")
@EntityScan(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.entity")
public class GoogleSearchClientApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GoogleSearchClientApp.class, args);
        GoogleSearchDirector googleSearchDirector = context.getBean(GoogleSearchDirector.class);
        googleSearchDirector.search();
        context.close();
    }
}