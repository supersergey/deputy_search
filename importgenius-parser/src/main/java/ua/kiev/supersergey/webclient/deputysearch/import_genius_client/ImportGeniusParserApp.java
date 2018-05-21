package ua.kiev.supersergey.webclient.deputysearch.import_genius_client;

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
public class ImportGeniusParserApp {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(ImportGeniusParserApp.class, args);
        ImportGeniusDirector director = context.getBean(ImportGeniusDirector.class);
        director.parse();
        context.close();
    }
}
