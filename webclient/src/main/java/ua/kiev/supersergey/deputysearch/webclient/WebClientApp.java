package ua.kiev.supersergey.deputysearch.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.dao")
@EntityScan(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.entity")
@ComponentScan(basePackages = {"ua.kiev.supersergey.deputysearch.commonlib.dao",
        "ua.kiev.supersergey.deputysearch.commonlib.entity",
        "ua.kiev.supersergey.deputysearch.webclient"})
@EnableAutoConfiguration
public class WebClientApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(WebClientApp.class, args);
    }
}
