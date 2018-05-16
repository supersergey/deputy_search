package ua.kiev.supersergey.deputysearch.searchengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.googlesearch.GoogleSearchClient;
import ua.kiev.supersergey.deputysearch.searchengine.httpclient.importgenius.ImportGeniusClient;

@SpringBootApplication
@EnableJpaRepositories
public class Application
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Director director = context.getBean(Director.class);



//        context
//                .getBean(GoogleSearchClient.class)
//                .doGet("ВІТАЛ ПЛЮС")
//                .filter(i -> i.contains("/suppliers/"))
//                .map(ImportGeniusClient::doGet)
//                .;
//
//        context.close();
    }
}
