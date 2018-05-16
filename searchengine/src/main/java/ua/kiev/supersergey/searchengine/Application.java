package ua.kiev.supersergey.searchengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.kiev.supersergey.searchengine.httpclient.googlesearch.GoogleSearchClient;
import ua.kiev.supersergey.searchengine.httpclient.googlesearch.entity.Items;
import ua.kiev.supersergey.searchengine.httpclient.importgenius.ImportGeniusClient;

@SpringBootApplication
@EnableJpaRepositories
public class Application
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context
                .getBean(GoogleSearchClient.class)
                .doGet("ВІТАЛ ПЛЮС")
                .filter(i -> i.contains("/suppliers/"))
                .map(ImportGeniusClient::doGet)
                .subscribe(System.out::println);

        context.close();
    }
}
