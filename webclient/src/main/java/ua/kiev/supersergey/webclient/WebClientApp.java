package ua.kiev.supersergey.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.dao")
@EntityScan(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.entity")
@EnableAutoConfiguration
public class WebClientApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(WebClientApp.class, args);
    }
}
