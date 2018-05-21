package ua.kiev.supersergey.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.dao")
@EntityScan(basePackages =
        "ua.kiev.supersergey.deputysearch.commonlib.entity")
@EnableWebMvc
public class WebClientApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(WebClientApp.class, args);
    }
}
