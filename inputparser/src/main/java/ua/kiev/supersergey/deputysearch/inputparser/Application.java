package ua.kiev.supersergey.deputysearch.inputparser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import reactor.core.publisher.Flux;
import ua.kiev.supersergey.deputysearch.inputparser.entity.InfoCard;
import ua.kiev.supersergey.deputysearch.inputparser.json.deserializer.DeclarationsDeserializer;
import ua.kiev.supersergey.deputysearch.inputparser.json.filereader.JsonInputFileReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by supersergey on 20.04.18.
 */
@SpringBootApplication
@EnableJpaRepositories
public class Application {
    @Value("${input.file.name}")
    private String fileName;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        try {
            String fileName = context.getEnvironment().getProperty("input.file.name");
            URL resource = Application.class.getClassLoader().getResource(fileName);
            byte[] rawJson = JsonInputFileReader.read(Paths.get(resource.getFile()));
            Flux<InfoCard> infoCardFlux = context.getBean(DeclarationsDeserializer.class).deserializeDeclarations(rawJson);
            infoCardFlux.subscribe(System.out::println);
            infoCardFlux.blockLast();
            context.close();
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read input data", ex);
        }
    }
}
