package ua.kiev.supersergey.deputysearch.import_genius_client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Application{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
