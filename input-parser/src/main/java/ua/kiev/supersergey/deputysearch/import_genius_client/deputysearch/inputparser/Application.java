package ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.inputparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by supersergey on 20.04.18.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages =
        "ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.dao")
@EntityScan(basePackages =
        "ua.kiev.supersergey.deputysearch.import_genius_client.deputysearch.commonlib.entity")
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.getBean(Director.class).runFlow();
        context.close();
    }
}
